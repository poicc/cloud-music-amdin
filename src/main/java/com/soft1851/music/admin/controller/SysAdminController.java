package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@RestController
@RequestMapping(value = "/sysAdmin")
@Slf4j
public class SysAdminController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 管理员登录
     * @param loginDto
     * @return String
     */
    @PostMapping("/login")
    public Map login(@RequestBody LoginDto loginDto) {
        log.info(loginDto.toString());
        return sysAdminService.login(loginDto);
    }

    @GetMapping("/data")
    public SysAdmin getInfoByName(@Param("name") String name) {
        return sysAdminService.getAdminByName(name);
    }

    @PutMapping("/update")
    ResponseResult updateInformation(@RequestBody @Valid SysAdmin sysAdmin){
        sysAdminService.updateInformation(sysAdmin);
        return ResponseResult.success();
    }

    @PutMapping("/update/password")
    public ResponseResult updatePassword(@RequestBody @Valid SysAdmin admin) {
        log.info(String.valueOf(admin));
        sysAdminService.updatePassword(admin);
        return ResponseResult.success();
    }


    @PostMapping("/single")
    public ResponseResult insertInfo(@RequestBody SysAdmin sysAdmin) {
        sysAdminService.insertSingle(sysAdmin);
        return ResponseResult.success();
    }
}