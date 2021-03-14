layui.use(['laypage', 'layer', 'table', 'form', 'element'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , e = layui.element //元素操作
        , form = layui.form //表单模块


    //获取父容器中的id
    var id =  parent.editid.editid;
    console.log(id)
    $.ajax({
        url: "/user/findUserById",
        type: "post",
        data: {
            id: id
        },
        success: function (res) {
            console.log(res)

            if (res.status == 200) {
                //给表单赋值
                form.val("checkForm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    "id": res.item.id
                    , "userName": res.item.userName // "name": "value"
                    , "nickName": res.item.nickName
                    , "account": res.item.account
                    , "password": res.item.password
                    , "gender": res.item.gender
                    , "age": res.item.age
                    , "email": res.item.email
                });

            }
            form.render()
        }
    })


    //自定义验证规则
    form.verify({
        title: function (value) {
            if (value.length < 5) {
                return '最小长度为5哦';
            }
        }
        , pass: [
            // /^[\S]{6,12}$/
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/
            // ,'密码必须8到12位，且不能出现空格'
            , '至少8个字符，至少1个大写字母，1个小写字母和1个数字'
        ]
        , account: [
            /^[a-zA-Z0-9_-]{4,16}$/
            , '至少4位，至多16位,不包含特殊字符哦'
        ]
        , age: [
            /^(?:[1-9]?\d|100)$/
            , '只能在1-99岁之间哦'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
    });


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

