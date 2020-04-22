package com.soft1851.music.admin.interceptor;

import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.exception.JwtException;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName JwtInterceptor
 * @Description Jwt拦截器
 * @Author mq_xu
 * @Date 2020/4/15
 * @Version 1.0
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 前置处理，拦截请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader("Authorization");
        //认证
        if (token == null) {
            log.info("### 用户未登录，请先登录 ###");
            //通过自定义异常抛出未登录的信息，由全局统一处理
            throw new JwtException("用户未登录，请先登录", ResultCode.USER_NOT_SIGN_IN);
        } else {
            //已经登录
            log.info("## token={}", token);
            //鉴权
            //根据id查到权限
            String getAdminMenuByAdminId = sysAdminService.getAdminMenuByAdminId(JwtTokenUtil.getUserId(token)).toString();
            //查询token的权限
            String tokenRole=JwtTokenUtil.getUserRole(token);
            log.info("根据id查权限"+sysAdminService.getAdminMenuByAdminId(JwtTokenUtil.getUserId(token)).toString());
            log.info("根据权限查id"+JwtTokenUtil.getUserRole(token));
            if (!getAdminMenuByAdminId.equals(tokenRole)) {
                log.info("### 用户权限不足 ###");
                //通过自定义异常抛出权限不足的信息，由全局统一处理
                throw new JwtException("用户权限不足", ResultCode.PERMISSION_NO_ACCESS);
            } else {
                //@Description 到期时间在当前时间之前
                if (JwtTokenUtil.isExpiration(token)) {
                    log.info("### token已失效 ###");
                    //通过自定义异常抛出token失效的信息，由全局统一处理
                    throw new JwtException("token已失效", ResultCode.USER_TOKEN_EXPIRES);
                } else {
                    //通过认证，放行到controller层
                    return true;
                }
            }
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
