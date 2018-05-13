package com.xzh.weixin.web.dao.model;

import java.util.Date;

public class RecomondModel {


    private long rmid;
    private String uid;
    private String search;
    private Date createTime;
    private Date updateTime;
    private Integer status;

    public long getRmid() {
        return rmid;
    }

    public void setRmid(long rmid) {
        this.rmid = rmid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}