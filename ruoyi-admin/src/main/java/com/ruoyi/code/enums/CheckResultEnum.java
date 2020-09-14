package com.ruoyi.code.enums;

/**
 * @ClassName CheckResultEnum
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/14 10:53
 * @Version 1.0
 */
public enum CheckResultEnum {
    PASS("审核通过",1),
    BACK("审核退回",0);
    private Integer value;
    private String name;
    CheckResultEnum(String name,Integer value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    /**
     * 根据ordinal获取label
     * @param ordinal
     * @return
     */
    public static String getLabelByOrdinal(Integer ordinal){
        CheckResultEnum[] enums = CheckResultEnum.values();
        for(CheckResultEnum e:enums){
            if(e.getValue()== ordinal){
                return e.getName();
            }
        }
        return "";
    }
}
