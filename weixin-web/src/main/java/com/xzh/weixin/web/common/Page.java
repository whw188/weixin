package com.xzh.weixin.web.common;

import java.util.List;

/**
 * Created by songlin on 2015/7/7.
 */
public class Page<T> {

    private int pageNO;
    private int pageSize;
    private int total;

    private List<T> dataList;
    private int totalPage;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int pageNO) {
        this.pageNO = pageNO;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        this.totalPage = this.total % this.pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }


}
