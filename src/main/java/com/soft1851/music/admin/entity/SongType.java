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
@TableName("song_type")
public class SongType extends Model<SongType> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    @TableId("type_id")
    private String typeId;

    /**
     *  类型名称
     */
    @TableField("type_name")
    private String typeName;

    /**
     * 歌曲数量
     */
    @TableField("song_count")
    private Integer songCount;

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
    private LocalDateTime createTime;

    /**
     * 分类
     */
    @TableField("type")
    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

}
