package com.soft1851.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soft1851.music.admin.annotation.ExcelVoAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author crq
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("song")
public class Song extends Model<Song> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌曲id
     */

    @TableId("song_id")
    @ExcelVoAttribute(name = "歌曲ID",column = 0)
    private String songId;

    /**
     * 歌曲名称
     */
    @TableField("song_name")
    @ExcelVoAttribute(name = "歌曲名称",column = 1)
    private String songName;

    /**
     * 排序id
     */
    @JsonIgnore
    @TableField("sort_id")
    private String sortId;

    /**
     * 歌手
     */
    @TableField("singer")
    @ExcelVoAttribute(name = "歌手",column = 2)
    private String singer;

    /**
     * 时长
     */
    @TableField("duration")
    @ExcelVoAttribute(name = "时长",column = 3)
    private String duration;

    /**
     * 封面图
     */
    @TableField("thumbnail")
    @ExcelVoAttribute(name = "封面图",column = 4)
    private String thumbnail;

    /**
     * 歌曲地址
     */
    @TableField("url")
    @ExcelVoAttribute(name = "歌曲地址",column = 5)
    private String url;

    /**
     * 歌词
     */
    @TableField("lyric")
    private String lyric;

    /**
     * 评论量
     */
    @TableField("comment_count")
    @ExcelVoAttribute(name = "评论量",column = 6,isNumber = true)
    private BigDecimal commentCount;

    /**
     * 收藏量
     */
    @TableField("like_count")
    @ExcelVoAttribute(name = "收藏量",column = 7,isNumber = true)
    private BigDecimal likeCount;

    /**
     * 播放量
     */
    @TableField("play_count")
    @ExcelVoAttribute(name = "播放量",column = 8,isNumber = true)
    private Integer playCount;

    /**
     * 删除标志
     */
    @TableField("delete_flag")
    private String deleteFlag;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ExcelVoAttribute(name = "创建时间",column = 9,isDateTime = true)
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.songId;
    }

}
