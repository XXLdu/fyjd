package com.ruoyi.code.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.code.domain.App.APPPicInfo;
import com.ruoyi.code.domain.SysFileInfo;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.mapper.SysFileInfoMapper;
import com.ruoyi.code.mapper.TrustMapper;
import com.ruoyi.code.service.IAPPService;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * app（APP）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
@Service
public class APPServiceImpl implements IAPPService
{
    @Autowired
    private TrustMapper trustMapper;

    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    @Override
    public int addTrust(Trust trust, List<SysFileInfo> sysFileInfos){
        //附件信息
        SysFileInfo sysFileInfo = new SysFileInfo();
        if(StringUtils.isNotEmpty(sysFileInfos)){
            for (int i=0;i<sysFileInfos.size();i++){
                sysFileInfo = sysFileInfos.get(i);
                sysFileInfo.setFatherId(trust.getId());
                sysFileInfo.setFileId(UUID.randomUUID().toString().replace("-",""));
                try {
                    sysFileInfoMapper.insertSysFileInfo(sysFileInfo);;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return trustMapper.insertTrust(trust);
    }

    @Override
    public int updTrust(Trust trust, List<SysFileInfo> sysFileInfos){
        sysFileInfoMapper.deleteSysFileInfoByFatherId(trust.getId());
        if(StringUtils.isNotEmpty(sysFileInfos)) {
            SysFileInfo sysFileInfo = new SysFileInfo();
            for (int i = 0; i < sysFileInfos.size(); i++) {
                sysFileInfo = sysFileInfos.get(i);
                sysFileInfo.setFatherId(trust.getId());
                sysFileInfo.setFileId(UUID.randomUUID().toString().replace("-",""));
                try {
                    sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return trustMapper.updateTrust(trust);
    }
}
