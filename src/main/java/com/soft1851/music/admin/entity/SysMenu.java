package com.soft1851.music.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级资源id，目录的parent_id为0
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 资源类型：1 目录  2 菜单  3 按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 资源名称
     */
    @TableField("title")
    private String title;

    /**
     * 资源url
     */
    @TableField("path")
    private String path;

    /**
     * 资源图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序号
     */
    @TableField("sort")
    private Integer sort;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
