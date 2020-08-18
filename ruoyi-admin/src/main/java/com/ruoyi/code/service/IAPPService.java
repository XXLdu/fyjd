package com.ruoyi.code.service;

import com.ruoyi.code.domain.SysFileInfo;
import com.ruoyi.code.domain.Trust;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * app（APPRAISALFILE）Service接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface IAPPService
{
    public int addTrust(Trust trust, List<SysFileInfo> sysFileInfos);
    public int updTrust(Trust trust, List<SysFileInfo> sysFileInfos);
}
