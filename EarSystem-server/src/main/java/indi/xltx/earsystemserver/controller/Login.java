package indi.xltx.earsystemserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.xltx.earsystemserver.config.auth.MyPasswordEncode;
import indi.xltx.earsystemserver.pojo.MyUser;
import indi.xltx.earsystemserver.service.UserService;

//用户登录与注册
@RestController
public class Login {

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

    @RequestMapping(value = "/register")
    public String register(@RequestBody MyUser rawUser) {
        //密码需要进行加密
        MyUser user = new MyUser(rawUser.getUsername(), passwordEncode.encode(rawUser.getPassword()), null);
        if (userService.isExist(user)) {
            return "用户已存在";
        }
        if (userService.addUser(user)) {
            return "成功";
        }
        else {
            return "失败";
        }
    }
}