package com.ruoyi.web.controller.system;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.service.ICommonService;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.code.util.CommonUtil;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;
    @Autowired
     private ITrustService trustService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private ISysUserService sysUserService;


    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
//        mmap.put("demoEnabled", Global.isDemoEnabled());
        mmap.put("demoEnabled", false);
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        Long userId = ShiroUtils.getUserId();
        List<Integer> roles = commonService.getRoleList(userId);
        SysUser user = sysUserService.selectUserById(ShiroUtils.getSysUser().getUserId());
        List<String> deptIds = Arrays.asList(new String[]{user.getDeptId()+""});
        ArrayList<String> processCode = (ArrayList<String>) CommonUtil.getTrustStatus(roles);
        TrustParam trustParam = new TrustParam();
        trustParam.setProcessCode(processCode);
        trustParam.setDepartmentId(deptIds);
        //获取待办事项
        mmap.put("list",trustService.selectTrustList(trustParam));
        return "main";
    }
}
