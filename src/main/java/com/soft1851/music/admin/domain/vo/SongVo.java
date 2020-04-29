package com.soft1851.music.admin.domain.vo;

import com.soft1851.music.admin.annotation.ExcelVoAttribute;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CRQ
 */
public class SongVo implements Serializable {
    @ExcelVoAttribute(name = "歌曲ID", column = 0)
    private String songId;
    @ExcelVoAttribute(name = "歌曲名称", column = 1)
    private String songName;
    @ExcelVoAttribute(name = "歌手姓名", column = 2)
    private String singer;
    @ExcelVoAttribute(name = "歌曲时长", column = 3)
    private String duration;
    @ExcelVoAttribute(name = "歌曲图片", column = 4)
    private String thumbnail;
    @ExcelVoAttribute(name = "歌曲链接", column = 5)
    private String url;
    @ExcelVoAttribute(name = "播放次数", column = 6, isNumber = true)
    private Integer playCount;
    @ExcelVoAttribute(name = "上架时间", column = 7, isDateTime = true)
    private LocalDateTime createTime;
}
