package com.ruoyi.code.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.code.domain.App.APPForm;
import com.ruoyi.code.domain.App.APPItem;
import com.ruoyi.code.domain.*;
import com.ruoyi.code.service.IAPPService;
import com.ruoyi.code.service.IAppraisalfileService;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.code.util.EncryptUtils;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 手机接口（APP）Controller
 *
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/app")
public class APPController extends BaseController
{
    private String prefix = "code/app";
    private int per_page = 15;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @Autowired
    private IAPPService appService;

    @Autowired
    private ITrustService trustService;

    @Autowired
    private IAppraisalfileService appraisalfileService;

/**********************公共接口common***********************/
    /**  登录接口 */
    @RequestMapping("/common/login")
    @ResponseBody
    public String login(String loginName, String password, String imei)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password, false);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);

            SysUser sysUser = new SysUser();
            sysUser = sysUserService.selectUserByLoginName(loginName);
            if(StringUtils.isEmpty(sysUser.getImei())||"".equals(sysUser.getImei())){
                sysUser.setImei(imei);
                sysUserService.updateUser(sysUser);
            }else if(!imei.equals(sysUser.getImei())){
                return BackMsg(200,"手机标识码验证未通过","");
            }
            return BackMsg(200,"登陆成功", EncryptUtils.getEncryptString(loginName));
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return BackMsg(500,msg,"");
        }
    }

    /**  公共配置接口 */
    @RequestMapping("/common/getConfig")
    @ResponseBody
    public String getConfig(String token){
        String loginName = getSysUser(token).getLoginName();
        SysDictData sysDictData = new SysDictData();
        List<SysDictData> dictList = sysDictDataService.selectDictDataList(sysDictData);
        return BackMsg(200,"操作成功",dictList);
    }

    /**  用户信息接口 */
    @RequestMapping("/common/getUser")
    @ResponseBody
    public String getUser(String token){
        SysUser sysUser = getSysUser(token);
        return BackMsg(200,"操作成功",sysUser);
    }

/**********************委托申请接口trust***********************/
    /**  获取申请表单接口 */
    @RequestMapping("/trust/getForm")
    @ResponseBody
    public String getTrustForm(String token){
        SysUser sysUser = getSysUser(token);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<APPForm> appForms = new ArrayList<>();
        //委托信息内容
        APPForm wtxx_appForm = new APPForm();
        wtxx_appForm.setCategory("委托信息");
        List<APPItem> wtxx_appItems = new ArrayList<>();
        wtxx_appItems.add(new APPItem("编号","code",1,UUID.randomUUID()+"",false,"","",null));
        wtxx_appItems.add(new APPItem("委托时间","time",4,dateFormat.format(new Date()),true,"^\\d{4}-\\d{1,2}-\\d{1,2}","",null));
        wtxx_appItems.add(new APPItem("委托单位","departmentName",1,sysUser.getDept().getDeptName(),false,"","",null));
        wtxx_appItems.add(new APPItem("鉴定专业","appraisalType",2,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",getChooseFromDict("appraisal_type")));
        wtxx_appItems.add(new APPItem("委托人一","name1",1,sysUser.getUserName(),false,"","",null));
        wtxx_appItems.add(new APPItem("联系方式","tel1",1,sysUser.getPhonenumber(),false,"","",null));
        wtxx_appItems.add(new APPItem("职务","job1",1,sysUser.getJob(),false,"","",null));
        wtxx_appItems.add(new APPItem("证件类型","cardType1",1,sysUser.getCardType(),false,"","",null));
        wtxx_appItems.add(new APPItem("证件编号","cardCode1",1,sysUser.getCardCode(),false,"","",null));
        wtxx_appItems.add(new APPItem("委托人二","name2",1,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        wtxx_appItems.add(new APPItem("联系方式","tel2",1,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        wtxx_appItems.add(new APPItem("职务","job2",2,"",true,"^[1]\\d{10}$","",getChooseFromDict("userjob")));
        wtxx_appItems.add(new APPItem("证件类型","cardType2",2,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",getChooseFromDict("card_type")));
        wtxx_appItems.add(new APPItem("证件编号","cardCode2",1,"",true,"/^[0-9]*$/","",null));
        wtxx_appForm.setItem(wtxx_appItems);
        appForms.add(wtxx_appForm);

        //案件信息内容
        APPForm ajxx_appForm = new APPForm();
        ajxx_appForm.setCategory("案件信息");
        List<APPItem> ajxx_appItems = new ArrayList<>();
        ajxx_appItems.add(new APPItem("案件名称","caseName",1,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        ajxx_appItems.add(new APPItem("案发时间","caseTime",4,dateFormat.format(new Date()),true,"^\\d{4}-\\d{1,2}-\\d{1,2}","",null));
        ajxx_appItems.add(new APPItem("案发地点","casePlace",1,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        ajxx_appItems.add(new APPItem("案件级别","caseLevel",2,"",true,"","",getChooseFromDict("case_level")));
        ajxx_appItems.add(new APPItem("案件性质","caseXingzhi",2,"",true,"","",getChooseFromDict("case_xingzhi")));
        ajxx_appItems.add(new APPItem("案件类型","caseType",2,"",true,"","",getChooseFromDict("case_type")));
        ajxx_appItems.add(new APPItem("简要案情","caseSummary",3,"",true,"","",null));
        ajxx_appForm.setItem(ajxx_appItems);
        appForms.add(ajxx_appForm);

        //被鉴定人信息内容
        APPForm bjdrxx_appForm = new APPForm();
        bjdrxx_appForm.setCategory("被鉴定人信息");
        List<APPItem> bjdrxx_appItems = new ArrayList<>();
        bjdrxx_appItems.add(new APPItem("姓名","appraisedtorName",1,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        bjdrxx_appItems.add(new APPItem("性别","appraisedtorSex",2,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",getChooseFromDict("sys_user_sex")));
        bjdrxx_appItems.add(new APPItem("出生日期","appraisedtorBirthday",4,"",true,"","",null));
        bjdrxx_appItems.add(new APPItem("身份证号","appraisedtorCardCode",1,"",true,"/^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$/","",null));
        bjdrxx_appItems.add(new APPItem("电话","appraisedtorTel",1,"",true,"^[1]\\d{10}$","",null));
        bjdrxx_appItems.add(new APPItem("家庭住址","appraisedtorHomePlace",1,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        bjdrxx_appItems.add(new APPItem("鉴定要求","appraisalAsk",3,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        bjdrxx_appItems.add(new APPItem("鉴定方法要求","appraisalWayAsk",3,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        bjdrxx_appItems.add(new APPItem("原鉴定情况","oldAppraisal",3,"",true,"^[\\u4e00-\\u9fa5]{0,}$","",null));
        bjdrxx_appForm.setItem(bjdrxx_appItems);
        appForms.add(bjdrxx_appForm);

        //附件列表信息内容
        APPForm fjlb_appForm = new APPForm();
        fjlb_appForm.setCategory("附件列表");
        List<APPItem> fjlb_appItems = new ArrayList<>();
        fjlb_appItems.add(new APPItem("附件上传","file",5,"",true,"","",null));
        fjlb_appForm.setItem(fjlb_appItems);
        appForms.add(fjlb_appForm);

        return BackMsg(200,"操作成功",appForms);
    }


    /**  新增申请接口 */
    @RequestMapping("/trust/add")
    @ResponseBody
    public String addTrust(Trust trust,List<MultipartFile> pic,String picInfo){

        trust.setId(UUID.randomUUID().toString().replaceAll("-",""));
        trust.setCode(trustService.getTrustCode());//系统生成编号：日期yyyymmdd+流水号001
        trust.setProcessCode(ProcessCode.wtj);//流程标识初始默认值为0

        int a = appService.addTrust(trust,pic,picInfo);
        if(a>0){
            return BackMsg(200,"操作成功","");
        }else{
            return BackMsg(500,"操作失败","");
        }
    }

    /**  修改申请接口 */
    @RequestMapping("/trust/update")
    @ResponseBody
    public String updateTrust(Trust trust,List<MultipartFile> pic,String picInfo){
        int a = appService.updTrust(trust,pic,picInfo);
        if(a>0){
            return BackMsg(200,"操作成功","");
        }else{
            return BackMsg(500,"操作失败","");
        }
    }

    /**  删除申请接口 */
    @RequestMapping("/trust/delete")
    @ResponseBody
    public String deleteTrust(String id){
        int a = trustService.deleteTrustById(id);
        if(a>0){
            return BackMsg(200,"操作成功","");
        }else{
            return BackMsg(500,"操作失败","");
        }
    }

    /**  查看申请接口 */
    @RequestMapping("/trust/detail")
    @ResponseBody
    public String detailTrust(String id){
        Trust trust = trustService.selectTrustById(id);
        return BackMsg(200,"操作成功",trust);
    }

    /**  查看申请列表接口 */
    @RequestMapping("/trust/list")
    @ResponseBody
    public String getTrustList(String token,int current_page){
        TrustParam trustParam = new TrustParam();
        SysUser sysUser = getSysUser(token);
        trustParam.setDepartmentName(sysUser.getDept().getDeptName());
        List<Trust> trustList = trustService.selectTrustList(trustParam);
        List trustPage = getPage(trustList,current_page);
        //构建分页
        Map<String,Object> page = new HashMap<>();
        page.put("total",trustList.size());
        page.put("per_page",per_page);
        page.put("current_page",current_page);
        page.put("last_page",getLastPage(trustList.size()));
        page.put("data",trustPage);
        return BackMsg(200,"操作成功",page);
    }

    /**  查看申请列表接口(带参数查询) */
    @RequestMapping("/trust/paramList")
    @ResponseBody
    public String getTrustList(String token,int current_page,TrustParam trustParam){
        SysUser sysUser = getSysUser(token);
        trustParam.setDepartmentName(sysUser.getDept().getDeptName());
        List<Trust> trustList = trustService.selectTrustList(trustParam);
        List trustPage = getPage(trustList,current_page);
        //构建分页
        Map<String,Object> page = new HashMap<>();
        page.put("total",trustList.size());
        page.put("per_page",per_page);
        page.put("current_page",current_page);
        page.put("last_page",getLastPage(trustList.size()));
        page.put("data",trustPage);
        return BackMsg(200,"操作成功",page);
    }

    private int getLastPage(int num){
        if ((num % per_page) == 0) {
            num = num / per_page;
        } else {
            num = (num / per_page) + 1;
        }
        return num;
    }
    private List getPage(List trustList,int current_page){
        if(current_page < 1) current_page = 1;
        int startNum = (current_page-1)*15;
        int endNum = current_page*15;
        if(trustList.size()<15){
            startNum=0;
            endNum=trustList.size();
        }else if(trustList.size()<startNum){
            endNum=trustList.size();
            startNum=endNum-15;
        }else if(trustList.size()>startNum&&trustList.size()<endNum){
            endNum = trustList.size();
        }
        trustList = trustList.subList(startNum, endNum);
        return trustList;
    }

    /**  查看委托审批列表接口 */
    @RequestMapping("/trustCheck/list")
    @ResponseBody
    public String getTrustCheckList(String token,String total,String per_page,String current_page,String last_page){
        TrustParam trustParam = new TrustParam();
        SysUser sysUser = getSysUser(token);
        List<SysRole> sysRoles = sysUser.getRoles();
        List<Long> roleIds = new ArrayList<>();
        for(int i=0;i<sysRoles.size();i++){
            roleIds.add(sysRoles.get(i).getRoleId());
        }
        ArrayList<String> processList = new ArrayList<>();

        List<Trust> trustList = new ArrayList<>();
        //待申请单位负责人审核
        boolean sqdwfzr = roleIds.contains(new Long(101));
        if(sqdwfzr){
            processList.add("1");
            trustParam.setProcessCode(processList);
            trustParam.setDepartmentName(sysUser.getDept().getDeptName());
            trustList.addAll(trustService.selectTrustList(trustParam));
        }
        //待鉴定单位委托接收人审核
        boolean jddwwtjsr = roleIds.contains(new Long(102));
        if(jddwwtjsr){
            processList.add("3");
            trustParam.setProcessCode(processList);
            trustList.addAll(trustService.selectTrustList(trustParam));
        }
        //去除list中的重复元素
        //trustList = new ArrayList<Trust>(new TreeSet<Trust>(trustList));
        //构建分页
        Map<String,Object> page = new HashMap<>();
        page.put("total",total);
        page.put("per_page",per_page);
        page.put("current_page",current_page);
        page.put("last_page",last_page);
        page.put("data",trustList);
        return BackMsg(200,"操作成功",page);
    }

    /**  查看文书审批列表接口 */
    @RequestMapping("/appraisalFile/list")
    @ResponseBody
    public String getppraisalFileCheckList(String token,String total,String per_page,String current_page,String last_page){
        TrustParam trustParam = new TrustParam();
        SysUser sysUser = getSysUser(token);
        List<SysRole> sysRoles = sysUser.getRoles();
        List<Long> roleIds = new ArrayList<>();
        for(int i=0;i<sysRoles.size();i++){
            roleIds.add(sysRoles.get(i).getRoleId());
        }
        ArrayList<String> processList = new ArrayList<>();

        List<Trust> trustList = new ArrayList<>();
        //待第二检验人审核
        {
            trustList.addAll(trustService.selectTester2List(sysUser.getUserName()));
        }
        //待签字授权人审核
        boolean qzsqr = roleIds.contains(new Long(104));
        if(qzsqr){
            processList.add("6");
            processList.add("76");
            trustParam.setProcessCode(processList);
            trustParam.setDepartmentName(sysUser.getDept().getDeptName());
            trustList.addAll(trustService.selectTrustList(trustParam));
        }
        //待机构负责人审核
        boolean jgfzr = roleIds.contains(new Long(105));
        if(	jgfzr){
            processList.add("7");
            trustParam.setProcessCode(processList);
            trustList.addAll(trustService.selectTrustList(trustParam));
        }
        //待发放
        {
            trustList.addAll(trustService.selectTester1List(sysUser.getUserName()));
        }
        //待签收
        {
            processList.add("9");
            trustParam.setProcessCode(processList);
            trustParam.setName1(sysUser.getUserName());
            trustList.addAll(trustService.selectTrustList(trustParam));
        }

        //去除list中的重复元素
        //trustList = new ArrayList<Trust>(new TreeSet<Trust>(trustList));
        //构建分页
        Map<String,Object> page = new HashMap<>();
        page.put("total",total);
        page.put("per_page",per_page);
        page.put("current_page",current_page);
        page.put("last_page",last_page);
        page.put("data",trustList);
        return BackMsg(200,"操作成功",page);
    }

    /**  查看文书内容 */
    @RequestMapping("/appraisalFile/getContent")
    @ResponseBody
    public String getAppraisalFileContent(String trustId){
        Appraisalfile appraisalfile = appraisalfileService.selectAppraisalfileByTrustId(trustId);
        return BackMsg(200,"操作成功",appraisalfile.getContent());
    }

    /**  审核接口 */
    @RequestMapping("/trustCheck/check")
    @ResponseBody
    public String trustCheck(String trustId,String status,String suggestion){
        Suggestion suggestion_ = new Suggestion();
        suggestion_.setParentid(trustId);
        suggestion_.setStatus(status);
        suggestion_.setSuggestion(suggestion);
        suggestion_.setTime(new Date());
        suggestion_.setUser_id(ShiroUtils.getUserId()+"");
        suggestion_.setUser_name(ShiroUtils.getLoginName());
        int a = trustService.trustProcess(suggestion_);
        if(a>0){
            return BackMsg(200,"操作成功","");
        }else{
            return BackMsg(500,"操作失败","");
        }
    }

    private String BackMsg(int code, String message, Object data){
        Map<String,Object> map = new HashMap<>();

        map.put("code",code);
        map.put("message",message);
        map.put("data",data);

        String json = JSON.toJSONString(map);
        return json;
    };

    private SysUser getSysUser(String token){
        String loginName = EncryptUtils.getDecryptString(token);
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        return sysUser;
    }

    private List<Map<String,String>> getChooseFromDict(String dictType){
        List<Map<String,String>> chooseList = new ArrayList<>();
        SysDictData dictData = new SysDictData();
        dictData.setDictType(dictType);
        List<SysDictData> dictList = sysDictDataService.selectDictDataList(dictData);
        for (int i=0;i<dictList.size();i++){
            dictData = dictList.get(i);
            Map<String,String> choose = new HashMap<>();
            choose.put("key",dictData.getDictLabel());
            choose.put("value",dictData.getDictValue());
            chooseList.add(choose);
        }
        return chooseList;
    };

}
