package com.pyf.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private int id;
    private boolean isMore;
    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
