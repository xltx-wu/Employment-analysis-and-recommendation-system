package indi.xltx.earsystemserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import indi.xltx.earsystemserver.config.auth.MyPasswordEncode;
import indi.xltx.earsystemserver.pojo.MyUser;
import indi.xltx.earsystemserver.service.UserService;

//用注册
@RestController
public class Register {

    private UserService userService;
    private MyPasswordEncode passwordEncode;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncode(MyPasswordEncode passwordEncode) {
        this.passwordEncode = passwordEncode;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody MyUserTemp rawUser, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "{\"message\":\"session为空\",\"success\":false}";
        }

        String captchaCode = (String) session.getAttribute("simpleCaptcha");
        if (!rawUser.getCode().equals(captchaCode)) {
            return "{\"message\":\"验证码错误\",\"success\":false}";
        }
        // 密码需要进行加密
        MyUser user = new MyUser(rawUser.getUsername(), passwordEncode.encode(rawUser.getPassword()), null);
        if (userService.isExist(user)) {
            return "{\"message\":\"用户名已存在\",\"success\":false}";
        }
        if (userService.addUser(user)) {
            return "{\"message\":\"注册成功！\",\"success\":true}";
        } else {
            return "{\"message\":\"注册失败\",\"success\":false}";
        }
    }

    /**
     * MyUserTemp
     */
    static class MyUserTemp extends MyUser {
        private String code;


        /**
         * @param code
         */
        public MyUserTemp() {

        }

        /**
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(String code) {
            this.code = code;
        }

    }
}