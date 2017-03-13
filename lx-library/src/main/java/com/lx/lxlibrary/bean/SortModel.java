package com.lx.lxlibrary.bean;

/**
 * Created by 李响
 * 创建日期 2017/2/27
 * 描述：
 */
public class SortModel {
    protected String name;
    protected String sortLetters;  //显示数据拼音的首字母

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
