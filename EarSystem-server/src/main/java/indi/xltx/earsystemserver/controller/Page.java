package indi.xltx.earsystemserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Page {

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/loginpage")
    public String loginpage() {
        return "login.html";
    }


    @RequestMapping(value = "/")
    public String login() {
        return "index.html";
    }

}
