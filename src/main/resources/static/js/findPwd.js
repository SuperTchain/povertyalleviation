layui.use(['form', 'layedit', 'jquery', 'layer','element'], function () {
    var form = layui.form
        , layedit = layui.layedit
        , $ = layui.$//jquery
        , layer = layui.layer
        , element = layui.element
    //生成验证码
    //layui的js里面自定义方法要加window.方法名


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
        ],
        confirmPass: function(value){
            if($('input[name=password]').val() !== value)
                return '两次密码输入不一致！';
        }
        , account: [
            /^[a-zA-Z0-9_-]{4,16}$/
            , '至少4位，至多16位,不包含特殊字符哦'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
    });

    // 获取图片验证码
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount; //当前剩余秒数

    window.getValidateCode = function () {
        let val = $("#account").val();  // 获取输入账户值

        curCount = count;
        document.getElementById("btnSendCode").setAttribute("disabled", "true"); //设置按钮为禁用状态
        $('#btnSendCode').addClass('layui-btn-disabled');
        $('#btnSendCode').text(curCount + "秒后重获");
        InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次

            $.ajax({
                type: "post",
                url: "/getValidateCode",
                data: {"account":val},//将整个表单字段传到后台接口
                success: function (res) {
                    if (res.status == 200) {
                        layer.alert("发送成功");
                        //跳转到主页面
                    } else {
                        //发送验证码错误
                        //账户或邮箱错误
                        layer.alert(res.message);
                    }
                }
            })
        // //禁止页面跳转
        return false;

    }


    //timer处理函数
    function SetRemainTime() {
        if(curCount == 0) {
            $('#btnSendCode').removeClass('layui-btn-disabled');
            $('#btnSendCode').text("重获验证码");
            window.clearInterval(InterValObj); // 停止计时器
            document.getElementById("btnSendCode").removeAttribute("disabled"); //移除禁用状态改为可用
        } else {
            curCount--;
            $('#btnSendCode').text(curCount + "秒后重获");
        }
    }

    // 监听submit提交
    // submit(submit_video):他是submit按钮的lay-filter取值
    form.on('submit(updatePwd)', function (data) {
        let val = $("#account").val();
        let code = $("#validate").val();
        let pass = $("#pass").val();
        console.log(val);
        $.ajax({
            type: "post",
            url: "/updatePwd",
            data: {"account":val,"code":code,"password":pass},//将整个表单字段传到后台接口
            success: function (res) {
                if (res.status == 200) {
                    layer.alert("修改成功");

                    //跳转到主页面
                    window.location.href = "/index";
                } else {
                    //发送验证码错误
                    //账户或邮箱错误
                    layer.alert(res.message);
                }
            }
        })
        // //禁止页面跳转
        return false;
    });


});