package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.entity.SysAdmin;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qj
 * @since 2020-04-21
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {
    /**
     * 根据name查询
     * @param name
     * @return
     */
     SysAdmin selectByName(String name);

    /**
     * 根据用户id查询用户信息及角色信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> getAdminMenuByAdminId(String userId);



}
