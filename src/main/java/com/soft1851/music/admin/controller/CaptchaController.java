package com.soft1851.music.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.soft1851.music.admin.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author CRQ
 */
@RestController
@Slf4j
public class CaptchaController {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    @Resource
    private RedisService redisService;
    @GetMapping("/captcha")
    public void defaultCaptcha(String key){
        log.info("**********************");
        log.info(key);
        log.info("**********************");
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletResponse response = sra.getResponse();
        String text = defaultKaptcha.createText();
        log.info(text);
//        sout只有在自己开发的机器上显示
//        log可以在服务器上显示
        //把验证码存放在Redis    有效时长10分钟
        redisService.save(key,text, 10);
//        生成验证码图片，将text写入，并通过response输出到客户端浏览器
        BufferedImage image = defaultKaptcha.createImage(text);
        assert response != null;
        response.setContentType("image/jpeg");
        //设置失效时间            不失效
        response.setDateHeader("Expires",10);
        try {
            ImageIO.write(image, "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.error("图片传输异常");
            e.printStackTrace();
        }
    }
}
