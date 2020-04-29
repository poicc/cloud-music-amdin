package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.entity.SongType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
public interface SongTypeService extends IService<SongType> {

    /**
     * 模糊查询
     * @return
     */
    List<SongType> vagueSelect(String context);


    /**
     * 查所有
     * @return
     */
    List<SongType> selectAll();


    /**
     * 分页查询
     * @param current
     * @param size
     * @return
     */
    List<SongType> getByPage(int current, int size);


}
