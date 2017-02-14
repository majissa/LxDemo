package com.lx.lxdemo.bean;

/**
 * Created by 李响
 * 创建日期 2017/2/6
 * 描述：
 */
public class ExpandViewBean {
    private boolean isExpand;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpand() {

        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
