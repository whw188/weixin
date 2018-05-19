package com.xzh.weixin.web.dao.model;

import java.util.Date;

public class CommentAgreeModel {


    private long id;
    private String uid;
    private long cmid;
    private Integer agree;
    private Integer tread;
    private Date createTime;
    private Date updateTime;
    private Integer status;


    public long getCmid() {
        return cmid;
    }

    public void setCmid(long cmid) {
        this.cmid = cmid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getTread() {
        return tread;
    }

    public void setTread(Integer tread) {
        this.tread = tread;
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