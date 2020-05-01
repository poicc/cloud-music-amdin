package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
public interface SysAdminService extends IService<SysAdmin> {
    /**
     * 登录
     *
     * @param loginDto
     * @return boolean
     */
     Map<String,Object> login(LoginDto loginDto);


    /**
     * 根据name查询Admin信息，包含其所有角色
     *
     * @param name
     * @return
     */
    SysAdmin getAdminAndRolesByName(String name);

    /**
     * 通过四项得到token
     * @param adminId
     * @param roles
     * @param secrect
     * @param expiresAt
     * @return
     */
    String getToken(String adminId, String roles, String secrect, Date expiresAt);

    /**
     * 通过账号获取用户信息
     * @param name
     * @return
     */
    SysAdmin getAdminByName(String name);

    /**
     * 修改个人资料
     * @param admin
     */
    void updateInformation(SysAdmin admin);

    /**
     * 修改密码
     * @param admin
     */
    void updatePassword(SysAdmin admin);

    /**
     * 单个新增用户
     * @param admin
     */
    void insertSingle(SysAdmin admin);

    /**
     * 查询所有用户
     * @return
     */
    Map<String, Object> selectAll();
}
