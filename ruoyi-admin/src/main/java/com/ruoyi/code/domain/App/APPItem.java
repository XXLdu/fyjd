package com.ruoyi.code.domain.App;

import java.util.List;
import java.util.Map;

public class APPItem {
    private String title;
    private String code;
    private int type;
    private String value;
    private boolean editable;
    private String regex;
    private String tips;
    private List<Map<String,String>> choose;

    public APPItem(String title,String code,int type,String value,Boolean editable,String regex,String tips,List<Map<String,String>> choose){
        super();
        this.title = title;
        this.code = code;
        this.type = type;
        this.value = value;
        this.editable = editable;
        this.regex = regex;
        this.tips= tips;
        this.choose = choose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public List<Map<String, String>> getChoose() {
        return choose;
    }

    public void setChoose(List<Map<String, String>> choose) {
        this.choose = choose;
    }
}
