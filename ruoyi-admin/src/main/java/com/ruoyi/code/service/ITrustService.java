package com.ruoyi.code.service;

import com.ruoyi.code.domain.Suggestion;
import com.ruoyi.code.domain.Trust;
import com.ruoyi.code.domain.TrustParam;
import com.ruoyi.system.domain.SysUser;

import java.util.List;

/**
 * 委托Service接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface ITrustService 
{
    /**
     * 查询委托
     * 
     * @param id 委托ID
     * @return 委托
     */
    public Trust selectTrustById(String id);

    /**
     * 查询委托列表
     * 
     * @param trustParam 委托
     * @return 委托集合
     */
    public List<Trust> selectTrustList(TrustParam trustParam);

    //查询第一鉴定人待审核列表
    public List<Trust> selectTester1List(String tester1);

    //查询第二鉴定人待审核列表
    public List<Trust> selectTester2List(String tester2);

    /**
     * 新增委托
     * 
     * @param trust 委托
     * @return 结果
     */
    public int insertTrust(Trust trust);

    /**
     * 修改委托
     * 
     * @param trust 委托
     * @return 结果
     */
    public int updateTrust(Trust trust);

    /**
     * 批量删除委托
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrustByIds(String ids);

    /**
     * 删除委托信息
     * 
     * @param id 委托ID
     * @return 结果
     */
    public int deleteTrustById(String id);

    /**
     * 委托流程
     *
     * @param suggestion
     * @return 结果
     */
    public int trustProcess(Suggestion suggestion);

    /**
     * 获取委托编号
     */
    public String getTrustCode();

    /**
     * 获取待办
     * @param user
     * @return
     */
    List<Trust> getHandleList(SysUser user);

}
