package com.soft1851.music.admin.interceptor;

import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CRQ
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;

    /**
     * 前置处理，拦截请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取post方式请求参数：
         * 获取验证码
         */
        String key = request.getParameter("key"); //根据参数名称获取到参数值
        String checkCode = request.getParameter("checkCode"); //根据参数名称获取到参数值
        log.info("输入的验证码：" + checkCode);
        log.info("真实的验证码：" + redisService.get(key));
        //判断验证码的有效性和正确性
        if (checkCode.equals(redisService.get(key).trim())) {
            //验证码有效且正确      放行
            return true;
        } else {
            //验证码错误或失效
            throw new CustomException("验证码错误或失效", ResultCode.USER_VERIFY_CODE_ERROR);
        }
    }

}
