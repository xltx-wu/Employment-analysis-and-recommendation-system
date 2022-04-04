package indi.xltx.earsystemserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {
    @RequestMapping(value = "/login")
    public String login(){
        return "成功";
    }
}