package com.ruoyi.code.controller;

import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 鉴定文书（APPRAISALFILE）Controller
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Controller
@RequestMapping("/code/appraisalfileCheck")
public class AppraisalfileCheckController extends BaseController
{
    private String prefix = "code/appraisalfileCheck";

    @Autowired
    private ITrustService trustService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转第二鉴定人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:dejdrCheckView")
    @GetMapping("/dejdrCheck")
    public String dejdrCheck()
    {
        return prefix + "/dejdrCheck";
    }

    /**
     * 查询第二鉴定人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:dejdrCheckList")
    @PostMapping("/dejdrCheckList")
    @ResponseBody
    public TableDataInfo dejdrCheckList(TrustParam trustParam)
    {
        startPage();
        SysUser sysUser = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        List<Trust> list = trustService.selectTester2List(sysUser.getUserName());
        return getDataTable(list);
    }

    /**
     * 跳转鉴定文书授权人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:sqrCheckView")
    @GetMapping("/sqrCheck")
    public String sqrCheck()
    {
        return prefix + "/sqrCheck";
    }

    /**
     * 查询鉴定文书授权人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:sqrCheckList")
    @PostMapping("/sqrCheckList")
    @ResponseBody
    public TableDataInfo sqrCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode, ProcessCode.dsqrshws);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    /**
     * 跳转鉴定文书机构负责人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:jgfzrCheckView")
    @GetMapping("/jgfzrCheck")
    public String jgfzrCheck()
    {
        return prefix + "/jgfzrCheck";
    }

    /**
     * 查询鉴定文书机构负责人审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:jgfzrCheckList")
    @PostMapping("/jgfzrCheckList")
    @ResponseBody
    public TableDataInfo jgfzrCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode, ProcessCode.djgfzrshws);
        trustParam.setProcessCode(processCode);

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    /**
     * 跳转待发放审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:dffCheckView")
    @GetMapping("/dffCheck")
    public String dffCheck()
    {
        return prefix + "/dffCheck";
    }

    /**
     * 查询待发放审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:dffCheckList")
    @PostMapping("/dffCheckList")
    @ResponseBody
    public TableDataInfo dffCheckList(TrustParam trustParam)
    {
        startPage();

        SysUser sysUser = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        List<Trust> list = trustService.selectTester1List(sysUser.getUserName());
        return getDataTable(list);
    }

    /**
     * 跳转待签收审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:dqsCheckView")
    @GetMapping("/dqsCheck")
    public String dqsCheck()
    {
        return prefix + "/dqsCheck";
    }

    /**
     * 查询待签收审核列表
     */
    @RequiresPermissions("code:appraisalfileCheck:dqsCheckList")
    @PostMapping("/dqsCheckList")
    @ResponseBody
    public TableDataInfo dqsCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode, ProcessCode.dqs);
        trustParam.setProcessCode(processCode);
        SysUser sysUser = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        trustParam.setName1(sysUser.getUserName());

        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }
}
