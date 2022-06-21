package cn.amigosoft.modules.bms.other.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.amigosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 通知表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_notice")
public class BmsNoticeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否上线 （0：上线 1：下线）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标识 （0：未删除 1：已删除）
     */
    private Integer del;
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;
}