package com.ruoyi.code.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.code.enums.CheckResultEnum;

import java.time.LocalDateTime;

/**
 * @ClassName CheckLogParam
 * @Description 审核日志实体类
 * @Author yangHui
 * @Date 2020/9/14 13:44
 * @Version 1.0
 */
public class CheckLog {
    //主键
    private String id;
    //审核日期
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime checkTime;
    //审核人id
    private Long checkUserId;
    //审核人姓名
    private String checkUserName;
    //审核状态0：审核驳回 1：审核通过
    @JsonIgnore
    private Integer checkStatus;
    //审核状态值
    private String checkStatusName;
    //审核类型
    private String checkType;
    //审核类型
    private String checkTypeName;
    //申请主键
    private String trustId;
    //审核原因
    private String checkReason;

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }
    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public long getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(long checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckSeason() {
        return checkReason;
    }

    public void setCheckSeason(String checkReason) {
        this.checkReason = checkReason;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckStatusName() {
        return CheckResultEnum.getLabelByOrdinal(this.checkStatus);
    }

    public void setCheckStatusName(String checkStatusName) {
        this.checkStatusName = checkStatusName;
    }
    public void setCheckUserId(Long checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

}
