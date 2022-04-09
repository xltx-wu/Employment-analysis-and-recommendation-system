package indi.xltx.earsystemserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.xltx.earsystemserver.pojo.MyUser;
import indi.xltx.earsystemserver.service.UserInfoService;
import indi.xltx.earsystemserver.service.UserService;

@RestController
public class UserInfoController {
    private UserInfoService infoService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setInfoService(UserInfoService infoService) {
        this.infoService = infoService;
    }

    @RequestMapping("")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        MyUser user = userService.getUserByName(userDetails.getUsername());
        infoService.
    }
}
