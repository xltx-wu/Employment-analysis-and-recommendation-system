package indi.xltx.earsystemserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Page {

    @RequestMapping("/loginpage")
    public String loginpage() {
        return "login.html";
    }

    @RequestMapping(value = "/")
    public String login() {
        return "index.html";
    }

}
