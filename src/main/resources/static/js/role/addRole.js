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
        , pass: [
            // /^[\S]{6,12}$/
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/
            // ,'密码必须8到12位，且不能出现空格'
            , '至少8个字符，至少1个大写字母，1个小写字母和1个数字'
        ]
        , account: [
            /^[a-zA-Z0-9_-]{4,16}$/
            , '至少4位，至多16位,不包含特殊字符哦'
        ]
        , age: [
            /^(?:[1-9]?\d|100)$/
            , '只能在1-99岁之间哦'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
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