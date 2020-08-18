package com.ruoyi.code.domain.App;

import java.util.List;

public class APPForm {

    private String category;
    private List<APPItem> item;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<APPItem> getItem() {
        return item;
    }

    public void setItem(List<APPItem> item) {
        this.item = item;
    }
}
