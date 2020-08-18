package com.ruoyi.code.mapper;

import com.ruoyi.code.domain.Appraisalfile;

import java.util.List;

/**
 * 鉴定文书（APPRAISALFILE）Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-06
 */
public interface AppraisalfileMapper 
{
    /**
     * 查询鉴定文书（APPRAISALFILE）
     * 
     * @param id 鉴定文书（APPRAISALFILE）ID
     * @return 鉴定文书（APPRAISALFILE）
     */
    public Appraisalfile selectAppraisalfileById(String id);

    public Appraisalfile selectAppraisalfileByTrustId(String trustId);

    /**
     * 查询鉴定文书（APPRAISALFILE）列表
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 鉴定文书（APPRAISALFILE）集合
     */
    public List<Appraisalfile> selectAppraisalfileList(Appraisalfile appraisalfile);

    /**
     * 新增鉴定文书（APPRAISALFILE）
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 结果
     */
    public int insertAppraisalfile(Appraisalfile appraisalfile);

    /**
     * 修改鉴定文书（APPRAISALFILE）
     * 
     * @param appraisalfile 鉴定文书（APPRAISALFILE）
     * @return 结果
     */
    public int updateAppraisalfile(Appraisalfile appraisalfile);

    /**
     * 删除鉴定文书（APPRAISALFILE）
     * 
     * @param id 鉴定文书（APPRAISALFILE）ID
     * @return 结果
     */
    public int deleteAppraisalfileById(String id);

    /**
     * 批量删除鉴定文书（APPRAISALFILE）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppraisalfileByIds(String[] ids);
}
