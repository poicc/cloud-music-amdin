package com.soft1851.music.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.domain.entity.SysRole;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.service.RedisService;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.JwtTokenUtil;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@Service
@Slf4j
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {


    @Resource
    private SysAdminMapper sysAdminMapper;
    @Resource
    private RedisService redisService;

    @Override
    public Map<String,Object> login(LoginDto loginDto) {
        //根据查到基础信息，主要是要用密码来判定
        SysAdmin admin = sysAdminMapper.getSysAdminByName(loginDto.getName());
//        String name = loginDto.getName();
//        admin.setName(name);
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator =  factory.getValidator();
//        Set<ConstraintViolation<SysAdmin>> validators = validator.validate(admin);
//        for( ConstraintViolation<SysAdmin> constraintViolation:validators){
//            log.error(constraintViolation.getMessage());
//            System.out.println(constraintViolation.getMessage());
//        }
        if (admin != null) {
            //客户端密码加密后和数据库的比对
            String pass = Md5Util.getMd5(loginDto.getPassword(), true, 32);
            if (admin.getPassword().equals(pass)) {
                //登录成功，取得admin的完整信息（包含所有角色）
                SysAdmin admin1 = sysAdminMapper.selectByName(loginDto.getName());
                //roles是个list，可能会是多个
                List<SysRole> roles = admin1.getRoles();
                String roleString = JSONObject.toJSONString(roles);
                log.info("管理员角色列表：" + roleString);
                //通过该管理员的id、roles、私钥、指定过期时间生成token
                String token = JwtTokenUtil.getToken(admin.getId(), JSONObject.toJSONString(roles),admin.getSalt(), new Date(System.currentTimeMillis() + 6000L * 1000L));
                //将私钥存入redis，在后面JWT拦截器中可以取出来对客户端请求头中的token解密
                redisService.set(admin1.getId(), admin1.getSalt(), 100L);
                Map<String, Object> map = new TreeMap<>();
                map.put("admin", admin1);
                map.put("token", token);
                return map;
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
    public SysAdmin getAdminAndRolesByName(String name) {
        return sysAdminMapper.selectByName(name);
    }

    @Override
    public String getToken(String adminId, String roles, String secrect, Date expiresAt) {
        return JwtTokenUtil.getToken(adminId, roles, secrect, expiresAt);
    }

    @Override
    public SysAdmin getAdminByName(String name) {
        QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        SysAdmin sysAdmin = sysAdminMapper.selectOne(wrapper);
        if (sysAdmin != null) {
            return sysAdmin;
        }
        throw new CustomException("用户不存在", ResultCode.USER_NOT_FOUND);
    }

    @Override
    public void updateInformation(SysAdmin admin) {
        UpdateWrapper<SysAdmin> wrapper = new UpdateWrapper<>();
        wrapper.set("name", admin.getName())
                .set("avatar", admin.getAvatar())
                .set("status", admin.getStatus())
               // .set("password", Md5Util.getMd5(admin.getPassword(), true, 32))
                .eq("id", admin.getId());
        try {
            sysAdminMapper.update(admin, wrapper);
        } catch (Exception e) {
            throw new CustomException("个人信息修改异常", ResultCode.DATABASE_ERROR);
        }
    }

    @Override
    public void updatePassword(SysAdmin admin) {
        UpdateWrapper<SysAdmin> wrapper = new UpdateWrapper<>();
        wrapper.set("name", admin.getName())
                .eq("id", admin.getId())
                .set("password", Md5Util.getMd5(admin.getPassword(), true, 32));
        try {
            sysAdminMapper.update(admin, wrapper);
        } catch (Exception e) {
            log.info("修改密码异常");
        }
    }


    @Override
    public void insertSingle(SysAdmin admin) {
        try {
            sysAdminMapper.insert(admin);
        } catch (Exception e) {
            throw new CustomException("用户新增异常", ResultCode.DATABASE_ERROR);
        }

    }

    @Override
    public Map<String, Object> selectAll() {
        return null;
    }
}
