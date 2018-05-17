package com.xzh.weixin.web.dao.model;

import com.xzh.weixin.web.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResourceModel {

    private Long rid;
    private String fileId;
    private String fileName;
    private String uid;
    private String author;
    private String title;
    private String summary;
    private String type;
    private String imageId;
    private Long cid;
    private Integer price;
    private Integer viewCount;
    private Integer agreeCount;
    private Integer shelfCount;
    private Date createTime;
    private Date updateTime;
    private Integer status;


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    public Integer getShelfCount() {
        return shelfCount;
    }

    public void setShelfCount(Integer shelfCount) {
        this.shelfCount = shelfCount;
    }

    public String getCreateTime() {
        return DateUtils.format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return DateUtils.format(updateTime);
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