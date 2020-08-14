package com.huayu.mybitsplus.layulutils;

import java.util.List;

public class DeptmenuUtils {
    private Integer id;
    private String title;
    private List children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
