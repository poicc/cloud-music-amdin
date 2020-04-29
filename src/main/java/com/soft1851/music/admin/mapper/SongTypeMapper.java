package com.soft1851.music.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.music.admin.domain.entity.SongType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
public interface SongTypeMapper extends BaseMapper<SongType> {

    /**
     * 根据name查询歌曲类型
     * @param name
     * @return
     */
    @Select("SELECT * FROM song_type WHERE type_name = #{typeName}")
    SongType selectByName(@Param("typeName") String name);


    /**
     * 查所有
     * @return
     */
    @Select("SELECT * FROM song_type")
    List<SongType> selectAll();

}
