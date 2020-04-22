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
@TableName("song_like")
public class SongLike extends Model<SongLike> {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞id
     */
    @TableId("like_id")
    private String likeId;

    /**
     * 点赞用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 歌曲id
     */
    @TableField("song_id")
    private String songId;

    /**
     * 评论id
     */
    @TableField("comment_id")
    private String commentId;

    /**
     * 视频id
     */
    @TableField("video_id")
    private String videoId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 歌单id
     */
    @TableField("song_list_id")
    private String songListId;


    @Override
    protected Serializable pkVal() {
        return this.likeId;
    }

}
