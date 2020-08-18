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
    public int addTrust(Trust trust, List<MultipartFile> pic, String picInfo){
        //附件信息
        int a = 1;
        if(StringUtils.isNotEmpty(picInfo)){
            SysFileInfo sysFileInfo = new SysFileInfo();
            APPPicInfo appPicInfo = new APPPicInfo();
            List<APPPicInfo> picInfos = JSONArray.parseArray(picInfo, APPPicInfo.class);
            Map<String,APPPicInfo> picInfosMap = new HashMap<>();
            for (int i=0;i<picInfos.size();i++){
                picInfosMap.put(picInfos.get(i).getKey(),picInfos.get(i));
            }
            for (int i=0;i<pic.size();i++){
                MultipartFile file = pic.get(i);
                appPicInfo = picInfosMap.get("pic"+(i+1));
                sysFileInfo.setFatherId(trust.getId());
                sysFileInfo.setFileType(appPicInfo.getType());
                sysFileInfo.setFileExplain(appPicInfo.getExplain());
                try {
                    a = fileUpload(file,sysFileInfo);
                } catch (IOException e) {
                    a = -1;
                    e.printStackTrace();
                }
            }
        }
        int b = -1;
        if(a>0){
            b = trustMapper.insertTrust(trust);
        }
        return b;
    }

    @Override
    public int updTrust(Trust trust, List<MultipartFile> pic, String picInfo){
        int a = 1;
        if(StringUtils.isNotEmpty(picInfo)) {
            SysFileInfo sysFileInfo = new SysFileInfo();
            //附件信息
            APPPicInfo appPicInfo = new APPPicInfo();
            List<APPPicInfo> picInfos = JSONArray.parseArray(picInfo, APPPicInfo.class);
            Map<String, APPPicInfo> picInfosMap = new HashMap<>();
            for (int i = 0; i < picInfos.size(); i++) {
                picInfosMap.put(picInfos.get(i).getKey(), picInfos.get(i));
            }
            for (int i = 0; i < pic.size(); i++) {
                MultipartFile file = pic.get(i);
                appPicInfo = picInfosMap.get("pic" + (i + 1));
                sysFileInfo.setFatherId(trust.getId());
                sysFileInfo.setFileType(appPicInfo.getType());
                sysFileInfo.setFileExplain(appPicInfo.getExplain());
                try {
                    a = fileUpload(file, sysFileInfo);
                } catch (IOException e) {
                    a = -1;
                    e.printStackTrace();
                }
            }
        }
        int b = -1;
        if(a>0){
            b = trustMapper.updateTrust(trust);
        }
        return b;
    }

    private int fileUpload(MultipartFile file, SysFileInfo sysFileInfo) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // 上传并返回新文件名称
        String filePath_name = FileUploadUtils.upload(filePath, file);
        sysFileInfo.setFileId(uuid);
        sysFileInfo.setFilePath(filePath_name);
        sysFileInfo.setFileName(file.getOriginalFilename());
        sysFileInfoMapper.deleteSysFileInfoByFatherId(sysFileInfo.getFatherId());
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }
}
