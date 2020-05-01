package com.soft1851.music.admin.mapper;

import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.domain.entity.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SysAdminMapperTest {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Test
    void selectByName() {
        SysAdmin sysAdmin = sysAdminMapper.selectByName("mqxu");
        System.out.println("admin基础信息");
        System.out.println(sysAdmin.getId());
        System.out.println(sysAdmin.getName());
        System.out.println(sysAdmin.getAvatar());
        System.out.println("他的所有角色");
        List<SysRole> roles = sysAdmin.getRoles();
        for (SysRole role : roles) {
            System.out.println(role.getRoleId() + ":" + role.getRoleName());
        }
    }

    @Test
    void getAdminByName() {
        System.out.println(sysAdminMapper.getSysAdminByName("mqxu"));
    }

    @Test
    void updateInformation() {
        SysAdmin sysAdmin = sysAdminMapper.selectByName("oupai");
        String name = sysAdmin.getName();
       // SysAdmin sysAdmin = sysAdminMapper.updateInformation("oupai","https://avatars3.githubusercontent.com/u/59445871?s=460&u=da5694544e03959d2e2c54ea5b2a29c67174cbcc&v=4");
    }
}