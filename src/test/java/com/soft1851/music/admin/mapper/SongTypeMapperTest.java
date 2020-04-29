package com.soft1851.music.admin.mapper;

import com.soft1851.music.admin.domain.entity.SongType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SongTypeMapperTest {

    @Resource
    private SongTypeMapper songTypeMapper;

    @Test
    void selectByName() {
        SongType songType = songTypeMapper.selectByName("综艺");
        System.out.println(songType);
    }


    @Test
    void selectAll(){
//        QueryWrapper<SongType> queryWrapper = new QueryWrapper<>();
//        List<SongType> sysRoles = songTypeMapper.selectList(queryWrapper);
//        System.out.println(sysRoles);
        System.out.println(songTypeMapper.selectAll());
    }

}