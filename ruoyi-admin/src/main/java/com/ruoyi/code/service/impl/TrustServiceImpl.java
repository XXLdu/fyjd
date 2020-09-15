package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.*;
import com.ruoyi.code.enums.CheckResultEnum;
import com.ruoyi.code.enums.WriteLogEnum;
import com.ruoyi.code.mapper.TrustMapper;
import com.ruoyi.code.service.ILogService;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 委托Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
public class TrustServiceImpl implements ITrustService {
    private static final Logger log = LoggerFactory.getLogger(TrustServiceImpl.class);

    @Autowired
    private TrustMapper trustMapper;

    @Autowired
    private ILogService logService;

    /**
     * 查询委托
     *
     * @param id 委托ID
     * @return 委托
     */
    @Override
    public Trust selectTrustById(String id) {
        return trustMapper.selectTrustById(id);
    }

    /**
     * 查询委托列表
     *
     * @param trustParam 委托查询参数实体类
     * @return 委托
     */
    @Override
    public List<Trust> selectTrustList(TrustParam trustParam) {
        List<Trust> trustList = trustMapper.selectTrustList(trustParam);
        for(int i=0;i<trustList.size();i++){
            trustList.get(i).setStatus(ProcessCode.getStatus(trustList.get(i).getProcessCode()));
        }
        return trustList;
    }

        //查询第一鉴定人待审核列表
        public List<Trust> selectTester1List(String tester1){
            return trustMapper.selectTester1List(tester1);
        };

        //查询第二鉴定人待审核列表
        public List<Trust> selectTester2List(String tester2){
            return trustMapper.selectTester2List(tester2);
        };


    /**
     * 新增委托
     *
     * @param trust 委托
     * @return 结果
     */
    @Override
    public int insertTrust(Trust trust) {
        return trustMapper.insertTrust(trust);
    }

    /**
     * 修改委托
     *
     * @param trust 委托
     * @return 结果
     */
    @Override
    public int updateTrust(Trust trust) {
        return trustMapper.updateTrust(trust);
    }

    /**
     * 删除委托对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTrustByIds(String ids) {
        return trustMapper.deleteTrustByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除委托信息
     *
     * @param id 委托ID
     * @return 结果
     */
    @Override
    public int deleteTrustById(String id) {
        return trustMapper.deleteTrustById(id);
    }

    /**
     * 委托流程
     *
     * @param suggestion
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int trustProcess(Suggestion suggestion) {
        Trust trust = selectTrustById(suggestion.getParentid());
        String processCode = ProcessCode.getProcessCode(trust.getProcessCode(), suggestion.getStatus());
        trust.setProcessCode(processCode);
        trust.setId(suggestion.getParentid());
        this.writeCheckLog(suggestion,processCode);
        return trustMapper.updateTrust(trust);
    }

    private int writeCheckLog (Suggestion suggestion,String processCode) {
        log.info("添加到审核日志表：{}",suggestion);
        CheckLog checkLog = new CheckLog();
        checkLog.setId(UUID.randomUUID().toString());
        if ("pass".equals(suggestion.getStatus())) {
            checkLog.setCheckStatus(CheckResultEnum.PASS.getValue());
        } else {
            checkLog.setCheckStatus(CheckResultEnum.BACK.getValue());
        }
        checkLog.setCheckSeason(suggestion.getSuggestion());
        checkLog.setCheckTime(LocalDateTime.now());
        checkLog.setCheckUserId(ShiroUtils.getUserId() );
        checkLog.setCheckUserName(ShiroUtils.getLoginName());
        checkLog.setTrustId(suggestion.getParentid());
        checkLog.setCheckType(processCode);
        return logService.addCheckLog(checkLog);
    }

    //获取委托编号
    @Override
    public String getTrustCode() {
        String code = trustMapper.getTrustCode();
        if (StringUtils.isEmpty(code) || "null".equals(code)) {
            code = "001";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        return dateName + code;
    }

    @Override
    public List<Trust> getHandleList(SysUser user) {
        return null;
    }
}
