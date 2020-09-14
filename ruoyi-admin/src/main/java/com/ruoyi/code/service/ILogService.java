package com.ruoyi.code.service;

import com.ruoyi.code.domain.CheckLog;

import java.util.List;

/**
 * @ClassName ILogService
 * @Description 日志接口
 * @Author yangHui
 * @Date 2020/9/14 13:43
 * @Version 1.0
 */
public interface ILogService {
    /**
     * 添加审核日志
     * @param checkLog
     * @return
     */
    int addCheckLog(CheckLog checkLog);

    /**
     * 查询审核日志
     * @param checkLog
     * @return
     */
    List<CheckLog> getCheckLogList(CheckLog checkLog);


}
