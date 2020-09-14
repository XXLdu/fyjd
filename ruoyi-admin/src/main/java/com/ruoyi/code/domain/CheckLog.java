package com.ruoyi.code.domain;

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
    private LocalDateTime checkTime;
    //审核人id
    private String checkUserId;
    //审核人姓名
    private String checkUserName;
    //审核状态0：审核驳回 1：审核通过
    @JsonIgnore
    private Integer checkStatus;
    //审核状态值
    private String checkStatusName;

    //审核原因
    private String checkSeason;
    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    private String trustId;

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
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
        return checkSeason;
    }

    public void setCheckSeason(String checkSeason) {
        this.checkSeason = checkSeason;
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

}
