package com.soft1851.music.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qj
 * @since 2020-04-21
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
    private String songListId;

    /**
     * 歌单名称
     */
    @TableField("song_list_name")
    private String songListName;

    /**
     * 封面
     */
    @TableField("thumbnail")
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
    private Integer songCount;

    /**
     * 收藏数
     */
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 评论数
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 删除标志
     */
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
