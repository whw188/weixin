package com.xzh.weixin.web.dao.model;

import java.util.Date;

public class CommentModel {


    private long cmid;
    private String fromUid;
    private String toUid;
    private long rid;
    private String content;
    private String agreeCount;
    private String treadCount;


    private Date createTime;
    private Date updateTime;
    private Integer status;

    public String getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(String agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getTreadCount() {
        return treadCount;
    }

    public void setTreadCount(String treadCount) {
        this.treadCount = treadCount;
    }

    public long getCmid() {
        return cmid;
    }

    public void setCmid(long cmid) {
        this.cmid = cmid;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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