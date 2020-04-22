package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.mapper.SysMenuMapper;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qj
 * @since 2020-04-21
 */
@Service
@Slf4j
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Resource
    private SysAdminMapper sysAdminMapper;

    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public boolean login(LoginDto loginDto) {
        SysAdmin admin1 = getAdmin(loginDto.getName());
        if (admin1 != null) {
            String pass = Md5Util.getMd5(loginDto.getPassword(), true, 32);
            if (admin1.getPassword().equals(pass)) {
                return true;
            } else {
                log.error("密码错误");
                throw new CustomException("密码错误", ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            log.error("用户名不存在");
            throw new CustomException("用户名不存在", ResultCode.USER_NOT_FOUND);
        }
    }

    @Override
    public SysAdmin getAdmin(String name) {
        Map<String, Object> params = new HashMap<>(8);
        params.put("name", name);
        List<SysAdmin> admins = sysAdminMapper.selectByMap(params);
        if (admins.size() > 0) {
            return sysAdminMapper.selectByMap(params).get(0);
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> getAdminMenuByAdminId(String userId) {
        //定义一个map
        Map<String, Object> map = new HashMap<>();
        //得到用户信息及角色信息
        List<Map<String, Object>> admin = sysAdminMapper.getAdminMenuByAdminId(userId);
        //取出用户的角色Id
        log.info(String.valueOf(admin));
        int roleId = Integer.parseInt(admin.get(0).get("role_id").toString());

        //通过角色Id得到该用户的权限
        List<Map<String, Object>> maps = menuMapper.getParentMenuByRoleId(roleId);
        //移除多于字段
        for (Map<String, Object> map1 : maps) {
            map1.remove("role_id");
            map1.remove("menu_id");
        }
        //将得到的两个集合写入map,返回数据
        map.put("user", admin);
        map.put("permission", maps);
        return map;
    }

    @Override
    public String getIdByAdmin(SysAdmin sysAdmin) {
        Map<String, Object> params = new HashMap<>(8);
        params.put("name", sysAdmin.getName());
        params.put("password", sysAdmin.getPassword());
        return sysAdminMapper.selectByMap(params).get(0).getId();
    }
}
