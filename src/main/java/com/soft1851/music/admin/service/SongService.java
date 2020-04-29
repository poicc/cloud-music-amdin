package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft1851.music.admin.domain.entity.Song;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
public interface SongService extends IService<Song> {
    /**
     * 查询所有歌单
     * @return
     */
    List<Map<String, Object>> selectAll();

    /**
     * 分页查询
     * @param current
     * @param size
     * @return
     */
    List<Song> getByPage(int current, int size);

    /**
     * 模糊查询
     * @return
     */
    List<Song> vagueSelect(String context);

//    /**
//     * 根据flag删除
//     * @param flag
//     * @return
//     */
//    List<Song> getSongByDate(String flag);

    /**
     * 批量插入
     * @param songs
     */
    void batchInsert(List<Song> songs);

    /**
     * 导出数据
     */
    void exportData() throws InterruptedException, IOException;
}
