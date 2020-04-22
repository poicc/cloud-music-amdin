package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.JwtTokenUtil;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qj
 * @since 2020-04-21
 */
@RestController
//@RequestMapping("/sysAdmin")
@Slf4j
public class SysAdminController {

    @Resource
    private SysAdminService sysAdminService;


    /**
     * 登录
     * @param username
     * @param password
     * @param checkCode
     * @return
     */
    @PostMapping("/login")
    public ResponseResult adminLogin(String username, String password,String key, String checkCode) {
        log.info(username, password);
//            验证用户是否正确
        LoginDto loginDto = new LoginDto();
        loginDto.setName(username);
        loginDto.setPassword(password);
        if (sysAdminService.login(loginDto)) {
            //验证通过
            SysAdmin sysAdmin = new SysAdmin();
            sysAdmin.setName(username);
            sysAdmin.setPassword(Md5Util.getMd5(password, true, 32));
            String userId=sysAdminService.getIdByAdmin(sysAdmin);
            //用userId和role来生成token，并指定过期时间
            Date expiresAt = new Date(System.currentTimeMillis() + 600L * 1000L);
            String token = JwtTokenUtil.getToken(userId, sysAdminService.getAdminMenuByAdminId(userId).toString(), expiresAt);
            //记录token
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert sra != null;
            //获取HttpServletResponse对象
            HttpServletResponse response = sra.getResponse();
            assert response != null;
            //将token放在响应头返回，此处需在跨域配置中配置allowedHeaders和allowedExposedHeaders
            response.setHeader("Authorization", token);
            System.out.println("登录成功");
            // 返回登录成功
            return ResponseResult.success(ResultCode.SUCCESS);
        }else {
            //密码错误,用户不存在
            return ResponseResult.failure(ResultCode.USER_NOT_FOUND);
        }
    }

    @GetMapping("/permission")
    public ResponseResult getPerMissions() {
        log.info("###  查询当前角色的权限 ###");
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader("Authorization");
        String userId = JwtTokenUtil.getUserId(token);
        Map<String, Object> adminMenuByAdminId = sysAdminService.getAdminMenuByAdminId(userId);
        return ResponseResult.success(adminMenuByAdminId);
    }
}
