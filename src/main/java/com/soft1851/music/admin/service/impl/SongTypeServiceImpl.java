package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.domain.entity.SongType;
import com.soft1851.music.admin.mapper.SongTypeMapper;
import com.soft1851.music.admin.service.SongTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@Service
public class SongTypeServiceImpl extends ServiceImpl<SongTypeMapper, SongType> implements SongTypeService {

    @Resource
    private SongTypeMapper songTypeMapper;


    @Override
    public List<SongType> vagueSelect(String context) {
        QueryWrapper<SongType> wrapper = new QueryWrapper<>();
        //like = like %变量%, leftLike = like %变量 rightLike = like 变量%
        wrapper.like("type_name", context);
        return songTypeMapper.selectList(wrapper);
    }

    @Override
    public List<SongType> selectAll() {
        return songTypeMapper.selectAll();
    }

    @Override
    public List<SongType> getByPage(int current, int size) {
        Page<SongType> page = new Page<>(current, size);
        QueryWrapper<SongType> wrapper = new QueryWrapper<>();
        Page<SongType> iPage = songTypeMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }
}
