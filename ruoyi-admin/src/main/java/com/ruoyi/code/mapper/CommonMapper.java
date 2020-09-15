package com.ruoyi.code.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CommonMapper
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/15 10:56
 * @Version 1.0
 */
@Repository
public interface CommonMapper {
    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    @Select("select role_id from sys_user_role WHERE user_id = #{userId}")
    List<Integer> getRoleList(@Param("userId") Long userId);
}
