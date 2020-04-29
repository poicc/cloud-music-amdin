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
@TableName("song_list")
public class SongList extends Model<SongList> {

    private static final long serialVersionUID = 1L;

    /**
     * 歌单id
     */
    @TableId("song_list_id")
    @ExcelVoAttribute(name = "歌单ID",column = 0)
    private String songListId;

    /**
     * 歌单名称
     */
    @TableField("song_list_name")
    @ExcelVoAttribute(name = "歌单名称", column = 1)
    private String songListName;

    /**
     * 封面
     */
    @TableField("thumbnail")
    @ExcelVoAttribute(name = "歌单封面图", column = 2)
    private String thumbnail;

    /**
     * 歌单类型
     */
    @TableField("type")
    private String type;

    /**
     * 歌曲数
     */
    @TableField("song_count")
    @ExcelVoAttribute(name = "歌曲数",column = 3,isNumber = true)
    private BigDecimal songCount;

    /**
     * 收藏数
     */
    @TableField("like_count")
    @ExcelVoAttribute(name = "收藏数",column = 4,isNumber = true)
    private BigDecimal likeCount;

    /**
     * 评论数
     */
    @TableField("comment_count")
    @ExcelVoAttribute(name = "评论数",column = 5,isNumber = true)
    private BigDecimal commentCount;

    /**
     * 删除标志
     */
    @JsonIgnore
    @TableField("delete_flag")
    private Integer deleteFlag;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ExcelVoAttribute(name = "创建时间",column = 6,isDateTime = true)
    private LocalDateTime createTime;

    /**
     * 播放量
     */
    @TableField("play_counts")
    private Integer playCounts;


    @Override
    protected Serializable pkVal() {
        return this.songListId;
    }

}
