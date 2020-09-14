package com.ruoyi.code.mapper;

import com.ruoyi.code.domain.CheckLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName LogMapper
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/14 14:07
 * @Version 1.0
 */
@Repository
public interface LogMapper {

    @Insert("insert into `trust_check_log` (`id`, `trustId`,`checkTime`, `checkUserId`,`checkUserName`,`checkStatus`,`checkReason` ) values " +
            "(#{check.id},#{check.trustId},#{check.checkTime},#{check.checkUserId},#{check.checkUserName},#{check.checkStatus},#{check.checkReason}) ")
    int saveCheckLog(@Param("checkLog") CheckLog checkLog);

    @Select("select * from trust_check_log where trustId = #{checkLog.trustId}")
    List<CheckLog> getCheckLogList(@Param("checkLog") CheckLog checkLog);
}
