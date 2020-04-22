package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.entity.SysMenu;

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
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色Id得到该用户的权限
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getParentMenuByRoleId(int roleId);

    /**
     * 根据父类id及角色id查询子类权限
     * @param roleId
     * @param parentId
     * @return
     */
    List<Map<String, Object>> getChildMenuByRoleId(int roleId,int parentId);

}
