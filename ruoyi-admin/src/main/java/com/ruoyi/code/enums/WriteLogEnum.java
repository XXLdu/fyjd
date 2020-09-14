package com.ruoyi.code.enums;

public enum WriteLogEnum {
    YES("需要",1),NO("不需要",1);

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

    private String name;
    private Integer value;
    WriteLogEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
