package com.soft1851.music.admin.service;

import com.soft1851.music.admin.dto.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SysAdminServiceTest {

    @Resource
    private SysAdminService sysAdminService;

    @Test
    void login() {
//        LoginDto loginDto = LoginDto.builder().name("mqxu").password("123456").build();
//        if(sysAdminService.login(loginDto) == true){
//            System.out.println("登录成功");
//        }else{
//            System.out.println("登录失败");
//        }
        LoginDto loginDto = LoginDto.builder().name("music").password("123456").build();
        assertTrue(sysAdminService.login(loginDto));
    }

    @Test
    void getAdmin() {
        System.out.println(sysAdminService.getAdmin("music"));
    }
}