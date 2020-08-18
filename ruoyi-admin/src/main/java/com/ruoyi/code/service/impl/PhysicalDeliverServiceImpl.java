package com.ruoyi.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.PhysicalDeliverMapper;
import com.ruoyi.code.domain.PhysicalDeliver;
import com.ruoyi.code.service.IPhysicalDeliverService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物证流转Service业务层处理
 * 
 * @author dqs
 * @date 2020-06-06
 */
@Service
public class PhysicalDeliverServiceImpl implements IPhysicalDeliverService 
{
    @Autowired
    private PhysicalDeliverMapper physicalDeliverMapper;

    /**
     * 查询物证流转
     * 
     * @param id 物证流转ID
     * @return 物证流转
     */
    @Override
    public PhysicalDeliver selectPhysicalDeliverById(String id)
    {
        return physicalDeliverMapper.selectPhysicalDeliverById(id);
    }

    /**
     * 查询物证流转列表
     * 
     * @param physicalDeliver 物证流转
     * @return 物证流转
     */
    @Override
    public List<PhysicalDeliver> selectPhysicalDeliverList(PhysicalDeliver physicalDeliver)
    {
        return physicalDeliverMapper.selectPhysicalDeliverList(physicalDeliver);
    }

    /**
     * 新增物证流转
     * 
     * @param physicalDeliver 物证流转
     * @return 结果
     */
    @Override
    public int insertPhysicalDeliver(PhysicalDeliver physicalDeliver)
    {
        return physicalDeliverMapper.insertPhysicalDeliver(physicalDeliver);
    }

    /**
     * 修改物证流转
     * 
     * @param physicalDeliver 物证流转
     * @return 结果
     */
    @Override
    public int updatePhysicalDeliver(PhysicalDeliver physicalDeliver)
    {
        return physicalDeliverMapper.updatePhysicalDeliver(physicalDeliver);
    }

    /**
     * 删除物证流转对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhysicalDeliverByIds(String ids)
    {
        return physicalDeliverMapper.deletePhysicalDeliverByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物证流转信息
     * 
     * @param id 物证流转ID
     * @return 结果
     */
    @Override
    public int deletePhysicalDeliverById(String id)
    {
        return physicalDeliverMapper.deletePhysicalDeliverById(id);
    }
}
