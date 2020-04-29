package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.domain.entity.Song;
import com.soft1851.music.admin.service.SongService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Resource
    private SongService songService;

    @GetMapping("/list")
    public List<Map<String, Object>> selectAll() {
        return songService.selectAll();
    }

    @GetMapping("/page")
    public List<Song> getByPage(@Param("currentPage") int currentPage, @Param("size") int size) {
        return songService.getByPage(currentPage, size);
    }

    @GetMapping("/vague")
    public List<Song> blurSelectSongList(@Param("context") String context) {
        return songService.vagueSelect(context);
    }

}
