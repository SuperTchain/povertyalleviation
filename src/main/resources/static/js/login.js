layui.use(['form', 'layedit', 'jquery', 'layer'], function () {
    var form = layui.form
        , layedit = layui.layedit
        , $ = layui.$//jquery
        , layer = layui.layer

    let val = $("#hiddenVcode").text();

    //自定义验证规则
    form.verify({
        title: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length < 1) {
                return '不能为空';
            }
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
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


    // //监听指定开关
    // form.on('switch(switchTest)', function(data){
    //   layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
    //     offset: '6px'
    //   });
    //   layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    // });


    // //监听提交
    // form.on('submit(submitlogin)', function(data){
    //     layer.alert(JSON.stringify(data.field), {
    //         title: '最终的提交信息'
    //     })
    // });

    // 登录操作(layui的表单提交)
    // form.on("submit(submitlogin)", function (data) {
    //     //data里面有form对象也有全部的表单字段
    //     $.ajax({
    //         type: "post",
    //         url: "/checkValidateCode",//自己登录校验的接口
    //         data: data.field,//将整个表单字段传到后台接口
    //         success: function (res) {
    //             if (res.status == 200) {
    //                 layer.alert("登录成功");
    //                 //跳转到主页面
    //             } else if(res.status==500) {
    //                 //验证码错误
    //                 layer.alert("验证码错误")
    //                 //重新刷新验证码
    //                 window.changeCode();
    //             }else{
    //                 //账户或密码错误
    //                 layer.alert("账户或者密码输入错误");
    //             }
    //         }
    //     })
    //     // //禁止页面跳转
    //     return false;
    // })


    //重新生成验证码
    //layui的js里面自定义方法要加window.方法名
    window.changeCode =function (){
        //获取img验证码标签
        $("#codeImg").attr("src", '/getCode?random=' + Math.random()).fadeIn();
    }

});