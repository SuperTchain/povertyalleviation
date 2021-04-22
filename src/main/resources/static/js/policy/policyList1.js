//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element','carousel','layer', 'element', 'form','laypage'], function () {
    var element = layui.element;
    var carousel = layui.carousel;
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $=layui.jquery;
    var laypage = layui.laypage;





    window.gotarget = function () {
        document.getElementById('header').scrollIntoView();
    }
    let textContent = $("#TemplateOptions").textContent;

//根据条件进行搜索
//     $("#indexSearch").click(function () {
//         $.ajax({
//             url: "/findProductLikeCondition",
//             type: "post",
//             data: {
//                 productName: textContent
//             },
//             success: function (res) {
//                 if (res.status == 200) {
//                     //index：layui便于记录弹框的索引
//                     layer.alert(res.item, function (index) {
//                         layer.close(index);//关闭弹框
//                         //重载表格
//                         user_table.reload();
//                     })
//                 }
//             }
//         })
//         alert("测试")
//     })

    //建造实例
    carousel.render({
        elem: '#test1'
        ,width: '100%' //设置容器宽度
        ,arrow: 'always' //始终显示箭头
        ,height: '500px'
        //,anim: 'updown' //切换动画方式
    });





    var page = 1;//当前页
    var limit = 10;//每页显示的数目
    var resCount, resData ;
    var resPage = renderPage1();

    let pid = $(".policyId").text();

    //渲染展示商品的html页面
    function renderPolicyHtml(data){
        var str = "";//用来存储html内容
        if(data.length > 0){
            $.each(data, function(v, o){
                str += "<div style='text-align:center;font-size: 22px'><div><a href='"+'/policy/toViewPolicy?id='+""+o.id+"' target='main'><span>"+o.policyTitle+"</span></a></div>";
                str += "<div style='display:none;'><label>政策ID:</label><span class='policyId' >"+o.id+"</span></div></div></div>";
            });
            $("#policy").html(str);
        }

    }


    //同步加载商品数据
    function renderPolicy(page, limit){
        $.ajax({
            async: false,
            url: '/policy/findPolicyByPolicyKind',
            data: {"policyKind":0,"page": page, "limit": limit},
            dataType: 'json',
            type:"post",
            success: function(result){
                console.info(result);
                resCount = result.total;
                resData = result.item;
                renderPolicyHtml(resData);
            }
        });
    }


    //分页的完整功能
    function renderPage1(){
        renderPolicy(page, limit);
        laypage.render({
            elem: 'layuipage'
            ,count: resCount
            ,limit: limit
            ,limits: [limit]
            ,curr: page
            ,theme: '#FFB800'
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj, first){
                console.info(obj);
                page = obj.curr;
                if(!first){
                    renderPolicy(page, limit);
                }
            }
        });
    }




    // window.viewPolicy=function (){
    //     let val = $("#productId").val();
    //     console.info(val)
    //     $.ajax({
    //         url: '/product/toViewPolicy',
    //         data: {"id":val},
    //         dataType: 'json',
    //         success: function(result){
    //             console.info(result);
    //         }
    //     });
    // }



});
