//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element','carousel','layer', 'element', 'form','laypage'], function () {
    var element = layui.element;
    var carousel = layui.carousel;
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $=layui.jquery;
    var laypage = layui.laypage;


    window.gotarget=function (){
        document.getElementById('header').scrollIntoView();
    }

});
