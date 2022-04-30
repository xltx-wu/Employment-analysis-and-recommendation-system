package indi.xltx.earsystemserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import indi.xltx.earsystemserver.utils.CaptchaImageGenerator;

import java.awt.image.BufferedImage;
import java.util.Date;

@RestController
public class CaptchaImageController {

    @RequestMapping(value = "/api/captcha", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage imageCode(HttpServletRequest request) throws Exception {
        String simpleCaptcha = CaptchaImageGenerator.CaptchaStr(4);
        HttpSession session = request.getSession();
        session.setAttribute("simpleCaptcha", simpleCaptcha);
        session.setAttribute("codeTime", new Date().getTime());

        BufferedImage image = CaptchaImageGenerator.getImageCode(86, 37, simpleCaptcha);
        System.out.println(image.toString());
        return image;
    }
}
