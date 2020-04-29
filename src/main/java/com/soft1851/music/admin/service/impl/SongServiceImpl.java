package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.domain.entity.Song;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SongMapper;
import com.soft1851.music.admin.service.SongService;
import com.soft1851.music.admin.util.ExcelConsumer;
import com.soft1851.music.admin.util.ExportDataAdapter;
import com.soft1851.music.admin.util.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Resource
    private SongMapper songMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.select("song_id", "song_name", "singer","duration","thumbnail","url","comment_count", "play_count", "create_time")
                .orderByDesc("play_count");
        List<Map<String, Object>> song = songMapper.selectMaps(wrapper);
        if(song != null){
            return song;
        }
        throw new CustomException("歌单查询异常", ResultCode.DATABASE_ERROR);
    }

    @Override
    public List<Song> getByPage(int current, int size) {
        Page<Song> page = new Page<>(current, size);
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        IPage<Song> iPage = songMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    @Override
    public List<Song> vagueSelect(String context) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        //like = like %变量%, leftLike = like %变量 rightLike = like 变量%
        wrapper.like("song_name", context)
                .or().like("singer", context);
        return songMapper.selectList(wrapper);

    }

//    @Override
//    public List<Song> getSongByDate(String flag) {
//        //判断flag是什么标志（周、月等）
//        TimeDto timeDto = new TimeDto();
//        switch (flag){
//            case "week":
//                timeDto.setWeek(flag);
//                break;
//            case "month":
//                timeDto.setMonth(flag);
//                break;
//            case "quarter":
//                timeDto.setQuarter(flag);
//                break;
//            default:
//                timeDto.setYesterday(flag);
//                break;
//        }
//        return songMapper.getSongByTimeParagraph(timeDto);
//    }

    @Override
    public void batchInsert(List<Song> songs) {

    }

    @Override
    public void exportData() throws InterruptedException, IOException {
        String excelPath = "D:\\temp1\\song.xlsx";
        //导出excel对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        //数据缓冲
        ExportDataAdapter<Song> exportDataAdapter = new ExportDataAdapter<>();
        //线程同步对象
        CountDownLatch latch = new CountDownLatch(2);
        //启动线程获取数据(生产者)
        ThreadPool.getExecutor().submit(() -> produceExportData(exportDataAdapter, latch));
        //启动线程导出数据（消费者）
        ThreadPool.getExecutor().submit(() -> new ExcelConsumer<>(Song.class, exportDataAdapter, sxssfWorkbook, latch, "歌曲数据").run());
        latch.await();
        //使用字节流写数据
        OutputStream outputStream = new FileOutputStream(excelPath);
        sxssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 生产者生产数据
     *
     * @param exportDataAdapter
     * @param latch
     */
    private void produceExportData(ExportDataAdapter<Song> exportDataAdapter, CountDownLatch latch) {
        List<Song> songs = songMapper.selectList(null);
        songs.forEach(exportDataAdapter::addData);
        log.info("数据生产完成");
        //数据生产结束
        latch.countDown();
    }


}
