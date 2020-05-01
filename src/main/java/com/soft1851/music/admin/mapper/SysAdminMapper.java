package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * 查询所有用户名
     * @return
     */
    @Select("SELECT name FROM sys_admin")
    List<SysAdmin> getAllName();

    /**
     * 修改个人信息
     * @param name
     * @param avatar
     * @return
     */
    @Update("UPDATE name,avatar FROM sys_admin WHERE name = #{name}")
    SysAdmin updateInformation(@Param("information")String name,String avatar);

    /**
     * 设置用户信息
     * @param sysAdmin
     * @return
     */
    int updateAdmin(SysAdmin sysAdmin);

    /**
     * 根据Id查用户信息 用于修改资料
     * @param id
     * @return
     */
    @Select("SELECT id,name,password,avatar FROM sys_admin WHERE id = #{id}")
    SysAdmin getAdminByAdminId(@Param("id") String id);
}
