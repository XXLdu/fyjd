package com.ruoyi.code.service;

import java.util.List;

/**
 * @ClassName ICommonService
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/15 10:52
 * @Version 1.0
 */
public interface ICommonService {
    //获取用户角色
    List<Integer> getRoleList(Long userId);
}
