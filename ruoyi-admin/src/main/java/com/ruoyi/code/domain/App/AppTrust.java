package com.ruoyi.code.domain.App;

import com.alibaba.fastjson.JSON;
import com.ruoyi.code.domain.SysFileInfo;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 委托对象 trust
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public class AppTrust extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** token */
    private String token;

    /** ID	主键 UUID() */
    private String id;

    /** 编号	系统按照编号规则自动生成 */
    @Excel(name = "编号	系统按照编号规则自动生成")
    private String code;

    /** 委托时间 */
    @Excel(name = "委托时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String time;

    /** 委托单位编号	关联单位表主键 */
    @Excel(name = "委托单位编号	关联单位表主键")
    private String departmentId;

    /** 委托单位名称 */
    @Excel(name = "委托单位名称")
    private String departmentName;

    /** 鉴定专业	存value汉字 */
    @Excel(name = "鉴定专业	存value汉字")
    private String appraisalType;

    /** 姓名1 */
    @Excel(name = "姓名1")
    private String name1;

    /** 联系方式1 */
    @Excel(name = "联系方式1")
    private String tel1;

    /** 职务1 */
    @Excel(name = "职务1")
    private String job1;

    /** 证件类型1 */
    @Excel(name = "证件类型1")
    private String cardType1;

    /** 证件编号1 */
    @Excel(name = "证件编号1")
    private String cardCode1;

    /** 姓名2 */
    @Excel(name = "姓名2")
    private String name2;

    /** 联系方式2 */
    @Excel(name = "联系方式2")
    private String tel2;

    /** 职务2 */
    @Excel(name = "职务2")
    private String job2;

    /** 证件类型2	存value汉字 */
    @Excel(name = "证件类型2	存value汉字")
    private String cardType2;

    /** 证件编号2 */
    @Excel(name = "证件编号2")
    private String cardCode2;

    /** 鉴定要求 */
    @Excel(name = "鉴定要求")
    private String appraisalAsk;

    /** 鉴定方法要求 */
    @Excel(name = "鉴定方法要求")
    private String appraisalWayAsk;

    /** 原鉴定情况 */
    @Excel(name = "原鉴定情况")
    private String oldAppraisal;

    /** 案件名称 */
    @Excel(name = "案件名称")
    private String caseName;

    /** 案发地点 */
    @Excel(name = "案发地点")
    private String casePlace;

    /** 案发时间 */
    @Excel(name = "案发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String caseTime;

    /** 案件性质	存value汉字 */
    @Excel(name = "案件性质	存value汉字")
    private String caseXingzhi;

    /** 案件类型	存value汉字 */
    @Excel(name = "案件类型	存value汉字")
    private String caseType;

    /** 案件级别	存value汉字 */
    @Excel(name = "案件级别	存value汉字")
    private String caseLevel;

    /** 简要案情 */
    @Excel(name = "简要案情")
    private String caseSummary;

    /** 被鉴定人姓名 */
    @Excel(name = "被鉴定人姓名")
    private String appraisedtorName;

    /** 被鉴定人性别 */
    @Excel(name = "被鉴定人性别")
    private String appraisedtorSex;

    /** 被鉴定人出生日期 */
    @Excel(name = "被鉴定人出生日期")
    private String appraisedtorBirthday;

    /** 被鉴定人身份证号*/
    @Excel(name = "被鉴定人身份证号")
    private String appraisedtorCardCode;

    /** 被鉴定人联系电话 */
    @Excel(name = "被鉴定人联系电话")
    private String appraisedtorTel;

    /** 被鉴定人家庭住址 */
    @Excel(name = "被鉴定人家庭住址")
    private String appraisedtorHomePlace;


    /** 流程id
     * 0，待提交【默认值】；10，所长退回；
     * 1，所长待审核；
     * 2，待二次提交；32，鉴定单位退回；
     * 3，待鉴定单位审核；
     * 4，待检验；
     * 5，待生成鉴定文书；65，授权人退回； 75，机构负责人退回；
     * 6，待授权人审核文书；76机构负责人退回；
     * 7，待机构负责人审核文书；
     * 8，完成。*/
    @Excel(name = "流程id")
    private String processCode;

    private String status;

    /** 提醒时间/鉴定文书出具时间/鉴定结束时间	确认受理时计算21日后时间，补充材料和会诊会重新计算时间 */
    @Excel(name = "提醒时间/鉴定文书出具时间/鉴定结束时间	确认受理时计算21日后时间，补充材料和会诊会重新计算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String appraisalEndTime;

    private String file;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }
    public void setDepartmentId(String departmentId) 
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() 
    {
        return departmentId;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setAppraisalType(String appraisalType) 
    {
        this.appraisalType = appraisalType;
    }

    public String getAppraisalType() 
    {
        return appraisalType;
    }
    public void setName1(String name1) 
    {
        this.name1 = name1;
    }

    public String getName1() 
    {
        return name1;
    }
    public void setTel1(String tel1) 
    {
        this.tel1 = tel1;
    }

    public String getTel1() 
    {
        return tel1;
    }
    public void setJob1(String job1) 
    {
        this.job1 = job1;
    }

    public String getJob1() 
    {
        return job1;
    }
    public void setCardType1(String cardType1) 
    {
        this.cardType1 = cardType1;
    }

    public String getCardType1() 
    {
        return cardType1;
    }
    public void setCardCode1(String cardCode1) 
    {
        this.cardCode1 = cardCode1;
    }

    public String getCardCode1() 
    {
        return cardCode1;
    }
    public void setName2(String name2) 
    {
        this.name2 = name2;
    }

    public String getName2() 
    {
        return name2;
    }
    public void setTel2(String tel2) 
    {
        this.tel2 = tel2;
    }

    public String getTel2() 
    {
        return tel2;
    }
    public void setJob2(String job2) 
    {
        this.job2 = job2;
    }

    public String getJob2() 
    {
        return job2;
    }
    public void setCardType2(String cardType2) 
    {
        this.cardType2 = cardType2;
    }

    public String getCardType2() 
    {
        return cardType2;
    }
    public void setCardCode2(String cardCode2) 
    {
        this.cardCode2 = cardCode2;
    }

    public String getCardCode2() 
    {
        return cardCode2;
    }
    public void setAppraisalAsk(String appraisalAsk) 
    {
        this.appraisalAsk = appraisalAsk;
    }

    public String getAppraisalAsk() 
    {
        return appraisalAsk;
    }
    public void setAppraisalWayAsk(String appraisalWayAsk) 
    {
        this.appraisalWayAsk = appraisalWayAsk;
    }

    public String getAppraisalWayAsk() 
    {
        return appraisalWayAsk;
    }
    public void setOldAppraisal(String oldAppraisal) 
    {
        this.oldAppraisal = oldAppraisal;
    }

    public String getOldAppraisal() 
    {
        return oldAppraisal;
    }
    public void setCaseName(String caseName) 
    {
        this.caseName = caseName;
    }

    public String getCaseName() 
    {
        return caseName;
    }
    public void setCasePlace(String casePlace) 
    {
        this.casePlace = casePlace;
    }

    public String getCasePlace() 
    {
        return casePlace;
    }
    public void setCaseTime(String caseTime)
    {
        this.caseTime = caseTime;
    }

    public String getCaseTime()
    {
        return caseTime;
    }
    public void setCaseXingzhi(String caseXingzhi) 
    {
        this.caseXingzhi = caseXingzhi;
    }

    public String getCaseXingzhi() 
    {
        return caseXingzhi;
    }
    public void setCaseType(String caseType) 
    {
        this.caseType = caseType;
    }

    public String getCaseType() 
    {
        return caseType;
    }
    public void setCaseLevel(String caseLevel) 
    {
        this.caseLevel = caseLevel;
    }

    public String getCaseLevel() 
    {
        return caseLevel;
    }
    public void setCaseSummary(String caseSummary) 
    {
        this.caseSummary = caseSummary;
    }

    public String getCaseSummary() 
    {
        return caseSummary;
    }
    public void setProcessCode(String processCode)
    {
        this.processCode = processCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessCode()
    {
        return processCode;
    }
    public void setAppraisalEndTime(String appraisalEndTime)
    {
        this.appraisalEndTime = appraisalEndTime;
    }

    public String getAppraisalEndTime()
    {
        return appraisalEndTime;
    }

    public String getAppraisedtorName() {
        return appraisedtorName;
    }

    public void setAppraisedtorName(String appraisedtorName) {
        this.appraisedtorName = appraisedtorName;
    }

    public String getAppraisedtorSex() {
        return appraisedtorSex;
    }

    public void setAppraisedtorSex(String appraisedtorSex) {
        this.appraisedtorSex = appraisedtorSex;
    }

    public String getAppraisedtorBirthday() {
        return appraisedtorBirthday;
    }

    public void setAppraisedtorBirthday(String appraisedtorBirthday) {
        this.appraisedtorBirthday = appraisedtorBirthday;
    }

    public String getAppraisedtorCardCode() {
        return appraisedtorCardCode;
    }

    public void setAppraisedtorCardCode(String appraisedtorCardCode) {
        this.appraisedtorCardCode = appraisedtorCardCode;
    }

    public String getAppraisedtorTel() {
        return appraisedtorTel;
    }

    public void setAppraisedtorTel(String appraisedtorTel) {
        this.appraisedtorTel = appraisedtorTel;
    }

    public String getAppraisedtorHomePlace() {
        return appraisedtorHomePlace;
    }

    public void setAppraisedtorHomePlace(String appraisedtorHomePlace) {
        this.appraisedtorHomePlace = appraisedtorHomePlace;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("time", getTime())
            .append("departmentId", getDepartmentId())
            .append("departmentName", getDepartmentName())
            .append("appraisalType", getAppraisalType())
            .append("name1", getName1())
            .append("tel1", getTel1())
            .append("job1", getJob1())
            .append("cardType1", getCardType1())
            .append("cardCode1", getCardCode1())
            .append("name2", getName2())
            .append("tel2", getTel2())
            .append("job2", getJob2())
            .append("cardType2", getCardType2())
            .append("cardCode2", getCardCode2())
            .append("appraisalAsk", getAppraisalAsk())
            .append("appraisalWayAsk", getAppraisalWayAsk())
            .append("oldAppraisal", getOldAppraisal())
            .append("caseName", getCaseName())
            .append("casePlace", getCasePlace())
            .append("caseTime", getCaseTime())
            .append("caseXingzhi", getCaseXingzhi())
            .append("caseType", getCaseType())
            .append("caseLevel", getCaseLevel())
            .append("caseSummary", getCaseSummary())
            .append("processCode", getProcessCode())
            .append("appraisalEndTime", getAppraisalEndTime())
            .append("appraisedtorName", getAppraisedtorName())
            .append("appraisedtorSex", getAppraisedtorSex())
            .append("appraisedtorBirthday", getAppraisedtorBirthday())
            .append("appraisedtorCardCode", getAppraisedtorCardCode())
            .append("appraisedtorTel", getAppraisedtorTel())
            .append("appraisedtorHomePlace", getAppraisedtorHomePlace())
            .toString();
    }

    public static Trust getTrust(AppTrust appTrust) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Trust trust = new Trust();
        trust.setId(appTrust.getId());
        trust.setCode(appTrust.getCode());
        Date time = new Date();
        try {
            time = sdf.parse(appTrust.getCaseTime());
        } catch (Exception e) {
            time = null;
        }
        trust.setTime(time);
        trust.setDepartmentName(appTrust.getDepartmentName());
        trust.setDepartmentId(appTrust.getDepartmentId());
        trust.setAppraisalType(appTrust.getAppraisalType());
        trust.setName1(appTrust.getName1());
        trust.setTel1(appTrust.getTel1());
        trust.setJob1(appTrust.getJob1());
        trust.setCardType1(appTrust.getCardType1());
        trust.setCardCode1(appTrust.getCardCode1());
        trust.setName2(appTrust.getName2());
        trust.setTel2(appTrust.getTel2());
        trust.setJob2(appTrust.getJob2());
        trust.setCardType2(appTrust.getCardType2());
        trust.setCardCode2(appTrust.getCardCode2());
        trust.setAppraisalAsk(appTrust.getAppraisalAsk());
        trust.setAppraisalWayAsk(appTrust.getAppraisalWayAsk());
        trust.setOldAppraisal(appTrust.getOldAppraisal());
        trust.setCaseName(appTrust.getCaseName());
        trust.setCasePlace(appTrust.getCasePlace());
        Date caseTime = new Date();
        try {
            caseTime = sdf.parse(appTrust.getCaseTime());
        } catch (Exception e) {
            caseTime = null;
        }
        trust.setCaseTime(caseTime);
        trust.setCaseXingzhi(appTrust.getCaseXingzhi());
        trust.setCaseType(appTrust.getCaseType());
        trust.setCaseLevel(appTrust.getCaseLevel());
        trust.setCaseSummary(appTrust.getCaseSummary());
        trust.setProcessCode(appTrust.getProcessCode());
        trust.setAppraisedtorName(appTrust.getAppraisedtorName());
        trust.setAppraisedtorSex(appTrust.getAppraisedtorSex());
        Date appraisedtorBirthday = new Date();
        try {
            appraisedtorBirthday = sdf.parse(appTrust.getCaseTime());
        } catch (Exception e) {
            appraisedtorBirthday = null;
        }
        trust.setAppraisedtorBirthday(appraisedtorBirthday);
        trust.setAppraisedtorCardCode(appTrust.getAppraisedtorCardCode());
        trust.setAppraisedtorTel(appTrust.getAppraisedtorTel());
        trust.setAppraisedtorHomePlace(appTrust.getAppraisedtorHomePlace());
        return trust;
    }

    public static AppTrust getAppTrust(Trust trust) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        AppTrust appTrust = new AppTrust();
        appTrust.setId(trust.getId());
        appTrust.setCode(trust.getCode());
        appTrust.setTime(sdf.format(trust.getTime()));
        appTrust.setDepartmentName(trust.getDepartmentName());
        appTrust.setDepartmentId(trust.getDepartmentId());
        appTrust.setAppraisalType(trust.getAppraisalType());
        appTrust.setName1(trust.getName1());
        appTrust.setTel1(trust.getTel1());
        appTrust.setJob1(trust.getJob1());
        appTrust.setCardType1(trust.getCardType1());
        appTrust.setCardCode1(trust.getCardCode1());
        appTrust.setName2(trust.getName2());
        appTrust.setTel2(trust.getTel2());
        appTrust.setJob2(trust.getJob2());
        appTrust.setCardType2(trust.getCardType2());
        appTrust.setCardCode2(trust.getCardCode2());
        appTrust.setAppraisalAsk(trust.getAppraisalAsk());
        appTrust.setAppraisalWayAsk(trust.getAppraisalWayAsk());
        appTrust.setOldAppraisal(trust.getOldAppraisal());
        appTrust.setCaseName(trust.getCaseName());
        appTrust.setCasePlace(trust.getCasePlace());
        appTrust.setCaseTime(sdf.format(trust.getCaseTime()));
        appTrust.setCaseXingzhi(trust.getCaseXingzhi());
        appTrust.setCaseType(trust.getCaseType());
        appTrust.setCaseLevel(trust.getCaseLevel());
        appTrust.setCaseSummary(trust.getCaseSummary());
        appTrust.setProcessCode(trust.getProcessCode());
        appTrust.setAppraisedtorName(trust.getAppraisedtorName());
        appTrust.setAppraisedtorSex(trust.getAppraisedtorSex());
        appTrust.setAppraisedtorBirthday(sdf.format(trust.getAppraisedtorBirthday()));
        appTrust.setAppraisedtorCardCode(trust.getAppraisedtorCardCode());
        appTrust.setAppraisedtorTel(trust.getAppraisedtorTel());
        appTrust.setAppraisedtorHomePlace(trust.getAppraisedtorHomePlace());
        appTrust.setFile(JSON.toJSONString(trust.getSysFileInfos()));
        return appTrust;
    }
}
