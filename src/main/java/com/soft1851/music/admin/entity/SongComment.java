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
@TableName("song_comment")
public class SongComment extends Model<SongComment> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;

    /**
     * 歌曲id
     */
    @TableField("song_id")
    private String songId;

    /**
     * userid
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;

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

    /**
     * 视频id
     */
    @TableField("vido_id")
    private String vidoId;

    /**
     * 点赞量
     */
    @TableField("like_counts")
    private Integer likeCounts;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
