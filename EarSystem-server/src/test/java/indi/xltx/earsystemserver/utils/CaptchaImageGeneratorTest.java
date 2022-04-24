package indi.xltx.earsystemserver.utils;

import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CaptchaImageGeneratorTest {
    @Test
    void testCaptchaStr() {
        String captch = CaptchaImageGenerator.CaptchaStr(5);
        System.out.println(captch);
    }

    @Test
    void testGetImageCode() throws IOException {
        String captch = CaptchaImageGenerator.CaptchaStr(5);
        BufferedImage image = CaptchaImageGenerator.getImageCode(100, 30, captch);
        File myImage = new File("C:/Users/24633/Desktop", "test.jpeg");
        ImageIO.write(image, "jpeg", myImage);
    }

    @Test
    void testGetRandColor() {
        System.out.println(CaptchaImageGenerator.getRandColor(0, 200));
    }
}
