package com.soft1851.music.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author CRQ
 */
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.cjar.length","4");
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color.length","150,180,90");
        properties.setProperty("kaptcha.textproducer.font.color","red");
        properties.setProperty("kaptcha.textproducer.font.size","30");
        properties.setProperty("kaptcha.textproducer.font.names","微软雅黑，楷体，宋体");
        properties.setProperty("kaptcha.image.width","120");
        properties.setProperty("kaptcha.image.height","45");
        properties.setProperty("kaptcha.session.key","code");
        Config config  = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
