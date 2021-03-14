layui.use(['laypage', 'layer', 'table', 'form', 'element', 'laydate'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , e = layui.element //元素操作
        , form = layui.form //表单模块
        , laydate = layui.laydate//时间模块


    //获取父容器中的id
    var id = parent.editid.editid;
    console.log(id)
    $.ajax({
        url: "/product/findProductById",
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
                    , "productName": res.item.productName // "name": "value"
                    , "productPrice": res.item.productPrice
                    , "productionAddress": res.item.productionAddress
                    , "productNumber": res.item.productNumber
                    , "productDesc": res.item.productDesc
                    , "productStatus": res.item.productStatus
                });
                var d = res.item.productDate;
                console.log(d)
                //时间模板
                templet: function f(data) {
                    return showTime(data);
                }
                try {
                    laydate.render({
                        elem: '#productDate'
                        ,type: 'datetime'
                        , value: f(d)
                        ,trigger: 'click',
                    });
                } catch (e) {
                    $("#productDate").val(d);
                }
            }
            form.render()
        }
    })


    //自定义验证规则
    form.verify({
        title: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length < 1) {
                return '不能为空';
            }
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '首尾不能出现下划线\'_\'';
            }
        }
        , number: [
            /^[1-9]\d*$/
            , '请输入正整数'
        ]
        , name: [
            /^[\u4e00-\u9fa50-9A-Za-z]*$/
            , '不包含特殊字符哦'
        ]
        , price: [
            /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/
            , '格式错误，正确格式形如x.x'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
    });


    //监听submit提交
    //submit(submit_video):他是submit按钮的lay-filter取值
    form.on('submit(submit_product)', function (data) {
        console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //         //刷新父窗口
        window.parent.location.reload();
    });
})

