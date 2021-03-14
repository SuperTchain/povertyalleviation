layui.use(['laypage', 'layer', 'carousel','table', 'form', 'element', 'laydate'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , carousel = layui.carousel
        , e = layui.element //元素操作
        , form = layui.form //表单模块
        , laydate = layui.laydate//时间模块


    //获取父容器中的id
    var id = parent.productid.productid;



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
                    , "productKind": res.item.productKind
                    , "hotSaleStatus": res.item.hotSaleStatus
                });
                // var d = res.item.productDate;
                // console.log(d)
                // //时间模板
                // templet: function f(data) {
                //     return showTime(data);
                // }
                // try {
                //     laydate.render({
                //         elem: '#productDate'
                //         ,type: 'datetime'
                //         , value: f(d)
                //         ,trigger: 'click',
                //     });
                // } catch (e) {
                //     $("#productDate").val(d);
                // }
            }
            form.render()
        }
    })
})

