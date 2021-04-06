layui.use(['layer', 'form'], function(){
    $ = layui.jquery;
    var layer = layui.layer
        ,form = layui.form ;
    // 初始化 销售额
    var saleChart = echarts.init(document.getElementById('div_sale_chart'));
    // 销售额--显示标题，图例和空的坐标轴
    saleChart.setOption({
        title: {
            text: '月度_销售额_直方图'
        },
        tooltip: {},
        legend: {
            data:['销售额(元)']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '销售额(元)',
            type: 'bar',
            data: []
        }]
    });
    // 初始化 费用比较
    var compareChart = echarts.init(document.getElementById('div_compare_chart'));
    // 费用比较---配置相关信息
    compareChart.setOption({
        title: {
            text: '销售额/广告费_饼状图',
        },
        tooltip: {},
        legend: {
            data:['金额(元)']
        },
        series: [{
            name: '金额(元)',
            type: 'pie',
            itemStyle: {
                // 阴影的大小
                shadowBlur: 200,
                // 阴影水平方向上的偏移
                shadowOffsetX: 0,
                // 阴影垂直方向上的偏移
                shadowOffsetY: 0,
                // 阴影颜色
                shadowColor: 'rgba(0, 0, 0, 0.2)'
            },
            // roseType: 'angle',//设置为南丁格尔图，即饼的半径不一致
            radius: '80%',
            data:[]
        }]
    });
    // 动态加载的相关数据
    $.ajax({
        url: '/analysis/layuiEchart',
        type: "post",
        async: true,//异步
        complete: function (XHR, TS) {
            if (XHR.status != 200) {
                layer.alert("系统繁忙，稍后重试");
            }
        },
        success: function (result) {
            if(result.status==0){
                //填入 销售数据
                saleChart.setOption({
                    xAxis: {
                        data: result.item.sale.week
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '销售额(元)',
                        data: result.item.sale.fee
                    }]
                });
                //填入 费用比较
                compareChart.setOption({
                    series: [{
                        name: '金额(元)',
                        // 根据名字对应到相应的系列
                        data: result.item.total
                    }]
                });
            }else{
                layer.msg("服务器提示:" + result.message);
            }
        },
        error: function () {
            layer.alert("error");
        }
    });
});