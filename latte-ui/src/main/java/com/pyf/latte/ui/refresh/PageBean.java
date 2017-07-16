package com.pyf.latte.ui.refresh;

/**
 * 分页
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class PageBean {

    // 当前第几页
    private int pageIndex;
    // 总条数
    private int total;
    // 一页显示几条数据
    private int pageSize;
    // 当前显示了几条数据
    private int currentCount;
    // 加载延迟
    private int delay;

    public PageBean() {
    }

    public PageBean(int pageIndex, int total,
                    int pageSize, int currentCount, int delay) {
        this.pageIndex = pageIndex;
        this.total = total;
        this.pageSize = pageSize;
        this.currentCount = currentCount;
        this.delay = delay;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public PageBean setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PageBean setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public PageBean setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
        return this;
    }

    public int getDelay() {
        return delay;
    }

    public PageBean setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public PageBean addIndex() {
        pageIndex++;
        return this;
    }
}
