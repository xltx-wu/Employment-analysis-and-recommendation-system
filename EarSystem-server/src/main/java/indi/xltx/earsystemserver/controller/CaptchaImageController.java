package indi.xltx.earsystemserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import indi.xltx.earsystemserver.utils.CaptchaImageGenerator;

import java.awt.image.BufferedImage;
import java.util.Date;

@RestController("/api")
public class CaptchaImageController {

    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    public BufferedImage imageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String simpleCaptcha = CaptchaImageGenerator.CaptchaStr(4);
        HttpSession session = request.getSession();
        session.setAttribute("simpleCaptcha", simpleCaptcha);
        session.setAttribute("codeTime", new Date().getTime());

        BufferedImage image = CaptchaImageGenerator.getImageCode(86, 37, simpleCaptcha);
        return image;
    }
}
