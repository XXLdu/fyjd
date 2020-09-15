package com.ruoyi.code.controller;

import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.service.ICommonService;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 委托审核controller
 *
 * @author ruoyi
 * @date 2020-05-25
 */
@Controller
@RequestMapping("/code/trustCheck")
public class TrustCheckController extends BaseController
{
    private String prefix = "code/trustCheck";

    @Autowired
    private ITrustService trustService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转委托单位审核列表
     */
    @RequiresPermissions("code:trustCheck:wtCheckView")
    @GetMapping("/wtCheck")
    public String wtCheck()
    {
        return prefix+"/wtCheck";
    }

    /**
     * 查询委托单位审核列表
     */
    @RequiresPermissions("code:trustCheck:wtCheckList")
    @PostMapping("/wtCheckList")
    @ResponseBody
    public TableDataInfo wtCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode,ProcessCode.wtdwdsh);
        trustParam.setProcessCode(processCode);
        SysUser user = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        List<String> deptIds = Arrays.asList(new String[]{user.getDeptId()+""});
        trustParam.setDepartmentId(deptIds);
        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

    /**
     * 跳转鉴定单位审核列表
     */
    @RequiresPermissions("code:trustCheck:jdCheckView")
    @GetMapping("/jdCheck")
    public String jdCheck() {
        return prefix+"/jdCheck";
    }

    /**
     * 查询鉴定单位审核列表
     */
    @RequiresPermissions("code:trustCheck:jdCheckList")
    @PostMapping("/jdCheckList")
    @ResponseBody
    public TableDataInfo jdCheckList(TrustParam trustParam)
    {
        startPage();

        //设置流程code查询参数
        ArrayList<String> processCode = new ArrayList();
        Collections.addAll(processCode,ProcessCode.jddwdsh);
        trustParam.setProcessCode(processCode);
        SysUser user = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        List<String> deptIds = Arrays.asList(new String[]{user.getDeptId()+""});
        trustParam.setDepartmentId(deptIds);
        List<Trust> list = trustService.selectTrustList(trustParam);
        return getDataTable(list);
    }

}
