package com.ruoyi.code.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class Suggestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Date time;//审核时间
    private String user_id;//审核人id
    private String user_name;//审核人姓名
    private String status;//审核状态
    private String suggestion;//审核意见
    private String parentid;//所属流程id
    //是否记录到审核日志中 0：不需要 1：需要
    private Integer writeLog;
    public Integer getWriteLog() {
        return writeLog;
    }
    public void setWriteLog(Integer writeLog) {
        this.writeLog = writeLog;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("time", getTime())
                .append("user_id", getUser_id())
                .append("user_name", getUser_name())
                .append("suggestion", getSuggestion())
                .append("parentid", getParentid())
                .toString();
    }

}
