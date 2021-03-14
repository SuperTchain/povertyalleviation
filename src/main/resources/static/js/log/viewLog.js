layui.use(['laypage', 'layer', 'table', 'form', 'element', 'laydate'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , e = layui.element //元素操作
        , form = layui.form //表单模块
        , laydate = layui.laydate//时间模块


    //获取父容器中的id
    var id = parent.logId.logId;
    $.ajax({
        url: "/log/findLogById",
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
                    , "operation": res.item.operation
                    , "operationUrl": res.item.operationUrl
                });
                var d = res.item.operationTime;
                console.log(d)
                //时间模板
                templet: function f(data) {
                    return showTime(data);
                }
                try {
                    laydate.render({
                        elem: '#operationTime'
                        ,type: 'datetime'
                        , value: f(d)
                        ,trigger: 'click',
                    });
                } catch (e) {
                    $("#operationTime").val(d);
                }
            }
            form.render()
        }
    })
})

