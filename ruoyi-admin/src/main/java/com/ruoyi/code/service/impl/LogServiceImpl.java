package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.CheckLog;
import com.ruoyi.code.mapper.LogMapper;
import com.ruoyi.code.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/14 14:23
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    LogMapper logMapper;

    @Override
    public int addCheckLog(CheckLog checkLog) {
        return logMapper.saveCheckLog(checkLog);
    }

    @Override
    public List<CheckLog> getCheckLogList(CheckLog checkLog) {
        return null;
    }
}
