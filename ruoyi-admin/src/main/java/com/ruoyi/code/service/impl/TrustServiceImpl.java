package com.ruoyi.code.service.impl;

import com.ruoyi.code.domain.ProcessCode;
import com.ruoyi.code.domain.Suggestion;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.code.mapper.TrustMapper;
import com.ruoyi.code.service.ITrustService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 委托Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
public class TrustServiceImpl implements ITrustService {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private TrustMapper trustMapper;

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
            trustList.get(i).setProcessCode(ProcessCode.getStatus(trustList.get(i).getProcessCode()));
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
    @Override
    public int trustProcess(Suggestion suggestion) {
        Trust trust = selectTrustById(suggestion.getParentid());
        String processCode = ProcessCode.getProcessCode(trust.getProcessCode(), suggestion.getStatus());
        trust.setProcessCode(processCode);
        trust.setId(suggestion.getParentid());
        return trustMapper.updateTrust(trust);
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
}
