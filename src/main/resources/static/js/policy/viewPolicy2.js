layui.use(['laypage', 'layer', 'carousel', 'table', 'form', 'element', 'laydate', 'layedit'], function () {
    var laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , $ = layui.$//jquery模块
        , carousel = layui.carousel
        , e = layui.element //元素操作
        , form = layui.form //表单模块
        , laydate = layui.laydate//时间模块
        , layedit = layui.layedit

    var policyId = parent.productid.productid

    var html = layedit.build('txtComment',{hideTool:true}); //建立编辑器

    var content = layedit.getContent(html);

    //建造实例
    carousel.render({
        elem: '#test1'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        , height: '500px'
        //,anim: 'updown' //切换动画方式
    });


    $.ajax({
        url: "/policy/findPolicyById",
        type: "post",
        data: {
            id: policyId
        },
        success: function (res) {
            console.log(res)

            if (res.status == 200) {
                //给表单赋值
                form.val("checkForm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    "id": res.item.id
                    , "policyTitle": res.item.policyTitle // "name": "value"
                    , "publisher": res.item.publisher
                    , "source": res.item.source
                    , "viewed": res.item.viewed
                    , "policyKind": res.item.policyKind
                });
                layedit.setContent(html, res.item.policyContent);
                var d = res.item.publishTime;
                console.log(d)
                //时间模板
                templet: function f(data) {
                    return showTime(data);
                }
                try {
                    laydate.render({
                        elem: '#publishTime'
                        , type: 'datetime'
                        , value: f(d)
                        , trigger: 'click',
                    });
                } catch (e) {
                    $("#publishTime").val(d);
                }
            }
            form.render()
        }
    })
})

