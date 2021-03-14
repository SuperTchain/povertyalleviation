layui.use(['laypage', 'layer', 'table', 'form', 'element'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , e = layui.element //元素操作
        , form = layui.form //表单模块


    //获取父容器中的id
    var id = parent.userid.userid;
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
})

