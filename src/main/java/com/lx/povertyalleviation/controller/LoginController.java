package com.lx.povertyalleviation.controller;

import cn.dsna.util.images.ValidateCode;
import com.lx.povertyalleviation.annotations.RecordOperation;
import com.lx.povertyalleviation.pojo.User;
import com.lx.povertyalleviation.service.UserService;
import com.lx.povertyalleviation.utils.DateUtil;
import com.lx.povertyalleviation.utils.Result;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;


/**
 * @ClassName HelloController
 * @Description TODO 登录
 * @Author ASUS
 * @Date 2020/5/25 23:30
 * @Version 1.0
 */
@Controller
@Api(tags = "登录接口")
public class LoginController {
    /**
     * 引入service
     */
    @Autowired
    private UserService userService;

    /**
     * 开启日志
     */
    private static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * 邮箱验证码发送
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private User user;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Result result;


//    /**
//     * 登录校验
//     * @return 登录校验
//     */
//    @PostMapping(value = "/login")
//    @ResponseBody
//    @ApiOperation("登录校验")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username",value = "账户名",dataType = "String"),
//            @ApiImplicitParam(name = "password",value = "密码",dataType = "String"),
//            @ApiImplicitParam(name = "code",value = "验证码",dataType = "String"),
//    })
//    public Result login(String username,String password,String code,HttpServletRequest request){
//        Result result = userService.findUserByName(username, password, code, request);
//        return result;
//    }

    /**
     * 跳转注册界面
     *
     * @return 界面
     */
    @GetMapping(value = "/toregist")
    @ApiOperation("注册")
    @RecordOperation(name = "注册", url = "/regist")
    public String regist() {
        return "regist";
    }


    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 添加结果
     */
    @PostMapping("/regist")
    @ApiOperation(value = "用户注册")
    @RecordOperation(name = "用户注册", url = "/regist")
    public String regist(@ApiParam(name = "user", value = "用户实体类") User user,HttpServletRequest request) {
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        Result result = userService.addUser(user,roleId);
        logger.info("成功注册用户");
        return "login";
    }


    /**
     * 测试springboot整合thymeleaf
     *
     * @return 返回到success界面
     */
    @GetMapping(value = "/logout")
    @ApiOperation("登出")
    @RecordOperation(name = "登出", url = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "login";
    }


    /**
     * 跳转到欢迎界面
     *
     * @return 欢迎界面
     */
    @GetMapping("/welcome")
    @ApiOperation("跳转到欢迎界面")
    public String toWelcome() {
        return "welcome";
    }

    /**
     * 跳转到热卖界面
     *
     * @return 热卖界面
     */
    @GetMapping("/toloadproduct")
    @ApiOperation("跳转到热卖界面")
    public String toHostSale() {
        return "loadproduct";
    }

    /**
     * 登录成功后跳转到主页面(main.html)
     *
     * @param request 请求
     * @return 主界面
     */
    @GetMapping("/main")
    @ApiOperation("登录成功跳转到主界面")
    @RecordOperation(name = "登录成功", url = "/login")
    public String main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("", true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //没有登录
            return "login";
        } else {
            //正常登录返回首页
            return "main";
        }
    }

    /**
     * 跳转到Index界面
     *
     * @return index界面
     */
    @GetMapping("/index")
    @ApiOperation("跳转到index界面")
    public String toIndex() {
        return "index";
    }

    /**
     * 跳转到登录界面
     *
     * @return 登录界面
     */
    @GetMapping("/toLogin")
    @ApiOperation("跳转到登录界面")
    public String toLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object pwdzq = session.getAttribute("pwdzq");
        if (null == pwdzq) {
            return "login";
        }
        return "main";
    }

    /**
     * 登录后主界面的跳转
     *
     * @return 主界面
     */
    @GetMapping("/toMain")
    @ApiOperation("登录成功后主界面跳转")
    @RecordOperation(name = "跳转主页", url = "/toMain")
    public String toMain() {
        return "main";
    }


    /**
     * 获取验证码
     *
     * @param response 响应
     * @param request  请求
     */
    @GetMapping("/getCode")
    @ApiOperation("获取验证码")
    public void getCode(HttpServletResponse response, HttpServletRequest request) {
        //验证码验证
        //参数分别是：宽   高    字符数量    干扰线数量
        ValidateCode vs = new ValidateCode(120, 40, 5, 10);
        logger.info("生成的图片验证码" + vs.getCode());
        //将本地生成的验证码存到session中(持续时间到本次会话结束之前)
        //session是可以设置有效期的，服务器级的缓存，服务器停止，session就会被清空
        HttpSession session = request.getSession();
        //将验证码存放到session中
        session.setAttribute("vareficode", vs.getCode());
        //将验证码使用响应输出到前端页面
        try {
            vs.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error("错误", e);
        }
    }

    /**
     * 获取邮箱验证码
     *
     * @param session 会话
     * @param request  请求
     */
    @PostMapping("/getValidateCode")
    @ApiOperation("获取邮箱验证码")
    @ResponseBody
    public Result getValidateCode(HttpSession session, HttpServletRequest request) {
        String account = request.getParameter("account");

        //验证码验证 生产随机数
        String str = "";
        for (int i = 0; i <= 3; i++) {
            Random random = new Random();
            str += String.valueOf(random.nextInt(9));
        }
        //简单邮件发送
        SimpleMailMessage message = new SimpleMailMessage();
        //设置标题
        message.setSubject("测试验证码邮箱");
        message.setFrom("486195050@qq.com");
        message.setTo("2401700911@qq.com");

        message.setText("您的验证码是:" + str);
        System.out.println(str);
        javaMailSender.send(message);
//        将验证码存放到数据库中
        Result result = userService.addValidateCode(str, account, DateUtil.getNowDate());
        return result;
    }

    @PostMapping("/checkValidateCode")
    @ResponseBody
    public Result findUserByName(String account, String password, String code,HttpServletRequest request){
        Result result = userService.findUserByName(account, password, code, request);
        return result;
    }

    /**
     * 更新密码
     *
     * @param request
     */
    @PostMapping("/updatePwd")
    @ApiOperation("更新密码")
    @ResponseBody
    public Result updatePwd(HttpServletRequest request) {
        String code = request.getParameter("code");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        try {
            //判断是否有这个账户
            userService.findUserByName(account);
            String validateCode = userService.findValidateCode(account);
            if(null==validateCode){
                result.setMessage("请获取验证码");
                result.setStatus(401);
                return result;
            }
            //验证验证码
            if (validateCode .equals( code) && code != null) {
                String encode = bCryptPasswordEncoder.encode(password);
                Integer integer = userService.updateUserPwd(encode, account);
                if(integer>0){
                    result.setMessage("修改成功");
                    result.setStatus(200);
                    logger.info("修改成功");
                    //更新密码后删除验证码
                    Integer isDelete = userService.deleteValidate(account);
                    if (isDelete>0){
                        logger.info("更新密码后删除相应验证码");
                    }
                }
            }else {
                result.setStatus(400);
                result.setMessage("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


    @GetMapping("/findProductLikeCondition")
    public String tofindProductLikeCondition(){
        return "product/findProductLikeCondition";
    }
}
