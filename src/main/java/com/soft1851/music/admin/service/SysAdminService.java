package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qj
 * @since 2020-04-21
 */
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    boolean login(LoginDto loginDto);

    /**
     * 根据name查询Admin
     * @param name
     * @return
     */
    SysAdmin getAdmin(String name);

    /**
     * 根据用户id查询用户信息及角色信息
     * @param userId
     * @return
     */
    Map<String, Object> getAdminMenuByAdminId(String userId);


    /**
     * 根据用户查id
     * @param sysAdmin
     * @return
     */
    String getIdByAdmin(SysAdmin sysAdmin);
}
