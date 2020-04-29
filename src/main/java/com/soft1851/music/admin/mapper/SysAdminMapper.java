package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {
    /**
     * 根据name查询管理员信息，包含其所有角色
     * @param name
     * @return
     */
    SysAdmin selectByName(@Param("name") String name);

    /**
     * 根据name查询admin基础信息，用于个人信息，登录等
     * 避开其中的List<SysRole>属性，因为它没有映射字段
     * @param name
     * @return
     */
    @Select("SELECT * FROM sys_admin WHERE name = #{name}")
    SysAdmin getSysAdminByName(@Param("name") String name);
}
