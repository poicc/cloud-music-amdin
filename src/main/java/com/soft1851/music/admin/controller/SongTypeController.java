package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongType;
import com.soft1851.music.admin.service.SongTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@RestController
@RequestMapping("/songType")
public class SongTypeController {

    @Resource
    private SongTypeService songTypeService;

    @GetMapping("/vague")
    public List<SongType> blurSelectSongList(@Param("context") String context) {
        return songTypeService.vagueSelect(context);
    }

    @GetMapping("/all")
    public ResponseResult selectAll(){
        return ResponseResult.success(songTypeService.selectAll());
    }

}
