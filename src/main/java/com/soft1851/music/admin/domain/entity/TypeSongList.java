package com.soft1851.music.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@TableName("type_song_list")
public class TypeSongList extends Model<TypeSongList> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型歌单id
     */
    @TableId("id")
    private String id;

    /**
     * 类型id
     */
    @TableField("type_id")
    private String typeId;

    /**
     * 歌单id
     */
    @TableField("song_list_id")
    private String songListId;

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
     * 类型名称
     */
    @TableField("type_name")
    private String typeName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
