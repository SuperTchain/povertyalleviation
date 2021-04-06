//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element', 'carousel', 'layer', 'element', 'form', 'laypage'], function () {
    var element = layui.element;
    var carousel = layui.carousel;
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    var laypage = layui.laypage;


    window.gotarget = function () {
        document.getElementById('header').scrollIntoView();
    }
    let textContent = $("#TemplateOptions").textContent;

//根据条件进行搜索
    $("#indexSearch").click(function () {
        // $.ajax({
        //     url: "/findProductByCondition",
        //     type: "post",
        //     data: {
        //         productName: textContent
        //     },
        //     success: function (res) {
        //         if (res.status == 200) {
        //             //index：layui便于记录弹框的索引
        //             layer.alert(res.item, function (index) {
        //                 layer.close(index);//关闭弹框
        //                 //重载表格
        //                 user_table.reload();
        //             })
        //         }
        //     }
        // })
        alert("测试")
    })



});
