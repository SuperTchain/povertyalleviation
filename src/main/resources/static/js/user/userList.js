layui.use(['laypage', 'layer', 'table', 'form', 'element'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , e = layui.element //元素操作
        , form = layui.form //表单模块
        ,laypage=layui.laypage//分页

    //执行一个 table 实例
    var user_table = table.render({
        elem: '#userList'//表格的id
        , height: 600//表格的高度
        , url: '/user/findAllUser'//获取视频列表的后台接口(异步的)
        // 借助parseData 回调函数将其解析成 table 组件所规定的数据格式
        , parseData: function (res) { //res 后端返回给前端的数据(响应)
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        }
        , title: '用户列表'
        //分页参数默认值：page=1   limit=10;
        , page: true
        , limit: 10
        , toolbar: '#head-option' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , totalRow: true //开启合计行
        , cols: [[ //表头
            //如果想要获取的后台数据能够正确渲染到页面上
            // 需要field的值要跟实体类中的name一致
            //sort :是否开启当前列排序
            //width:每列的宽度，如果不写就是完整的自适应
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: '用户id', sort: true}
            , {field: 'userName', title: '用户姓名'}
            , {field: 'nickName', title: '用户昵称'}
            , {field: 'account', title: '用户账号'}
            , {field: 'password', title: '用户密码'}
            //unresize:true设置为true，代表不能拖动，默认是false，都能拖动
            , {field: 'gender', width: 80, title: '性别', templet: '#tansforgender'}
            , {field: 'age', width: 80, title: '年龄', sort: true}
            , {field: 'email', title: '邮箱'}
            , {fixed: 'right', width: 170, toolbar: '#barDemo'}
        ]]
    });



    //监听头工具栏事件
    //监听的table标签中的lay-filter的取值
    table.on('toolbar(userTable)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)//表格的id
            , data = checkStatus.data; //获取选中的数据(将数据封装成对象给你返回)
        //定义存放被删除id的数组
        var ids = [];
        //遍历data，取出选中的对象的id存放到数组中
        for (var i = 0; i < data.length; i++) {
            //数组添加数据：push
            ids.push(data[i].id);
        }
        //obj.event:是点击对应按钮的lay-event的取值
        switch (obj.event) {
            case 'add':
                layer.open({
                    //0（信息框，默认）1（页面层）2（iframe层)
                    type: 2,
                    content: "/user/toAddUser",
                    area: ["60%", "60%"],//控制宽高
                    shadeClose: true,//点击外部窗口关闭
                    shade: 0.8//弹层外区域透明度取值
                })
                break;
            case 'delete':
                console.log(data.length)
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    layer.confirm("确认要删除吗，删除后不能恢复", {btn: ['确定', '取消'], title: "提示"}, function () {
                        //将选中的id数组传递到后台，删除
                        $.ajax({
                            url: "/user/batchDelete",//后台删除的接口
                            type: "post",
                            data: {
                                //id数组
                                "ids": ids
                            },
                            //直接传输数组，需要将traditional设置为true
                            traditional: true,
                            //后台成功之后的回调函数
                            success: function (res) {
                                if (res.status == 200) {
                                    //index：layui便于记录弹框的索引
                                    layer.alert(res.item, function (index) {
                                        layer.close(index);//关闭弹框
                                        //重载表格
                                        user_table.reload();
                                    })
                                }
                            }
                        })
                    });

                }
                break;
        }
        ;
    });

    //监听行右边工具事件
    table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test是table lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        var id = data.id;
        if (layEvent === 'detail') {
            userid = {
                userid:data.id,
            };
            layer.open({
                //0（信息框，默认）1（页面层）2（iframe层)
                type: 2,
                content: "/user/toViewUser",
                title: "查看界面",
                area: ["60%", "60%"],//控制宽高
                shadeClose: true,//点击外部窗口关闭
                shade: 0.8,//弹层外区域透明度取值
            });

        } else if (layEvent === 'del') {
            layer.confirm("确认要删除吗，删除后不能恢复", {btn: ['确定', '取消'], title: "提示"}, function (index) {
                obj.del(); //前端表格的效果：将当前行删除，实际上重新刷新还是存在的
                layer.close(index);//关闭窗口
                //向服务端发送删除指令
                $.ajax({
                    url: "/user/deleteById",//后台删除的接口
                    type: "post",
                    data: {
                        id: data.id//要删除行的id
                    },
                    success: function (res) {
                        if (res.status == 200) {
                            //index：layui便于记录弹框的索引
                            layer.alert(res.item, function (index) {
                                layer.close(index);//关闭弹框
                                //重载表格
                                user_table.reload();
                            })
                        }
                    }
                })
            });
        } else if (layEvent === 'edit') {
            editid = {
                editid:data.id,
            };
            layer.open({
                //0（信息框，默认）1（页面层）2（iframe层)
                type: 2,
                content: "/user/toEditUser",
                title:"编辑界面",
                area: ["60%", "60%"],//控制宽高
                shadeClose: true,//点击外部窗口关闭
                shade: 0.8//弹层外区域透明度取值
            })
        }

    });


    //根据条件进行搜索
    $("#search").click(function () {
        //为了搜索之后便于重新渲染表格数据，我们使用重载
        //这里以搜索为例
        user_table.reload({
            where: { //设定异步数据接口的额外参数
                userid: $("#userid").val(),
                username: $("#username").val(),
                account: $("#account").val()
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            },
            url: "/user/search"
        });
    })
    //监听submit提交
    //submit(submit_video):他是submit按钮的lay-filter取值
    form.on('submit(submit_user)', function (data) {
        console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //         //刷新父窗口
        window.parent.location.reload();
    });

})

