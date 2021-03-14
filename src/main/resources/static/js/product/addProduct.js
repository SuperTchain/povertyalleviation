layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , $ = layui.$
        , laydate = layui.laydate;


    //自定义验证规则
    form.verify({
        title: function(value, item){ //value：表单的值、item：表单的DOM对象
            if (value.length < 1) {
                return '不能为空';
            }
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
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

    //日期时间选择器
    laydate.render({
        elem: '#productDate'
        ,type: 'datetime'
        //解决距离底部太近 闪退问题
        ,trigger: 'click',
    });

    //监听submit提交
    //submit(submit_video):他是submit按钮的lay-filter取值
    form.on('submit(submit_user)', function (data) {
        console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //         //刷新父窗口
        window.parent.location.reload();
    });


});