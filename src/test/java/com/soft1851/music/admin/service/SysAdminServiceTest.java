package com.soft1851.music.admin.service;

import com.soft1851.music.admin.domain.entity.SysAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysAdminServiceTest {

    @Resource
    private SysAdminService sysAdminService;
    @Test
    void updateInformation() {

        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setId("DE35D7CC05AF96A21D7ADFC8651E6614");
        sysAdmin.setName("poi");
        sysAdmin.setPassword("12345");
        sysAdmin.setAvatar("测试头像");
        sysAdminService.updateInformation(sysAdmin);
    }
}