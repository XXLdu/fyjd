package com.ruoyi.code.util;

import com.ruoyi.code.domain.ProcessCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommonUtil
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/15 9:31
 * @Version 1.0
 */
public class CommonUtil {


    /**
     * 通过角色获取需要返回的状态列表
     * @param roles
     * @return
     */
    public static List<String> getTrustStatus(List<Integer> roles) {
        List<String> returnList = new ArrayList<>();
        if(roles != null && roles.size() > 0) {
            roles.forEach(role->{
                List<String> list = getStatus(role);
                returnList.addAll(list);
            });
        }
        return  returnList;
    }

    private static List<String> getStatus (Integer role) {
        return ProcessCode.getHandleStatus(role);
    }

}
