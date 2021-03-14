//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element','carousel','layer', 'element', 'form','laypage'], function () {
    var element = layui.element;
    var carousel = layui.carousel;
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $=layui.jquery;
    var laypage = layui.laypage;

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

    let pid = $(".productId").text();

    //渲染展示商品的html页面
    function renderProductHtml(data){
        var str = "";//用来存储html内容
        if(data.length > 0){
            $.each(data, function(v, o){
                str += "<div style='margin:auto; margin-bottom:130px;  width:750px; height:200px; float:left; text-align:center' class='layui-col-md6'><div><a href='"+'/product/toViewProduct?id='+""+o.id+"' target='main'><img alt='' src='"+'/static/img/products/'+""+o.productImgName+"' width='250px' height='250px' style='border: #f8f9fa;border-radius：30px;'/></a></div>";
                str += "<div style='text-align:center'><div><label>商品名称:</label><span>"+o.productName+"</span></div>";
                str += "<div><label>价格:</label><span style='color:#FF3030;'>￥"+o.productPrice+"</span></div>";
                str += "<div><label>商品描述:</label><span>"+o.productDesc+"</span></div>";
                str += "<div><label>库存:</label><span style='color:#CDC9C9;'>"+o.productNumber+"</span></div>";
                str += "<div style='display:none;'><label>商品ID:</label><span class='productId' >"+o.id+"</span></div></div></div>";
            });
            $("#product").html(str);
        }

    }


    //同步加载商品数据
    function renderProduct(page, limit){
        $.ajax({
            async: false,
            url: '/product/findProductByCondition',
            data: {"productKind":2,"hostSaleStatus":null,"page": page, "limit": limit},
            dataType: 'json',
            type:"post",
            success: function(result){
                console.info(result);
                resCount = result.total;
                resData = result.item;
                renderProductHtml(resData);
            }
        });
    }


    //分页的完整功能
    function renderPage1(){
        renderProduct(page, limit);
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
                    renderProduct(page, limit);
                }
            }
        });
    }




    // window.viewProduct=function (){
    //     let val = $("#productId").val();
    //     console.info(val)
    //     $.ajax({
    //         url: '/product/toViewProduct',
    //         data: {"id":val},
    //         dataType: 'json',
    //         success: function(result){
    //             console.info(result);
    //         }
    //     });
    // }



});
