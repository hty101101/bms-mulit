package cn.amigosoft.modules.bms.dinning.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 接待餐审批表
 */
@Data
@ApiModel(value = "接待餐审批表 ")
public class BmsReceptionMealVerifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "接待餐ID")
    private Long receptionMealId;

    @ApiModelProperty(value = "审批意见 （0：同意 1：驳回）")
    private Integer opinion;

    @ApiModelProperty(value = "回复意见")
    private String replyContent;

    @ApiModelProperty(value = "下一个审批人员ID")
    private Long nextUserId;

    @ApiModelProperty(value = "级别 （0：提交申请 1：主管审批 2：办公室审批）")
    private Integer level;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除标识 （0：未删除 1：已删除）")
    private Integer del;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "创建人")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新人")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "创建人名称")
    private String creatorName;

    @ApiModelProperty(value = "创建人工号")
    private String creatorWorkNo;

    @ApiModelProperty(value = "下一个审批人员姓名")
    private String nextUserName;

    @ApiModelProperty(value = "下一个审批人员工号")
    private String nextUserWorkNo;


}