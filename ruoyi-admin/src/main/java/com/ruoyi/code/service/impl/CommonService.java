package com.ruoyi.code.service.impl;

import com.ruoyi.code.mapper.CommonMapper;
import com.ruoyi.code.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CommonService
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/15 10:53
 * @Version 1.0
 */
@Service
public class CommonService implements ICommonService {
    @Autowired
    CommonMapper commonMapper;

    @Override
    public List<Integer> getRoleList(Long userId) {
        return commonMapper.getRoleList(userId);
    }
}
