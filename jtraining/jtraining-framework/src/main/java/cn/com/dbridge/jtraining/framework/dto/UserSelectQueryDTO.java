package cn.com.dbridge.jtraining.framework.dto;

import cn.com.dbridge.jtraining.framework.base.PageDTO;

public class UserSelectQueryDTO extends PageDTO {
    /**
     * 番号または名前
     */
    private String noOrName;
    /**
     * タイプ
     */
    private Byte type;

    /**
     * ページ番号
     */
    private Integer offset;

    /**
     * レコード数
     */
    private Integer limit;

    /**
     * ページ番号
     */
    @Override
    public Integer getOffset() {
        return offset;
    }

    /**
     * ページ番号
     */
    @Override
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * レコード数
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * レコード数
     */
    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    /**
     * 番号または名前
     */
    public String getNoOrName() {
        return noOrName;
    }

    /**
     * 番号または名前
     */
    public void setNoOrName(String noOrName) {
        this.noOrName = noOrName;
    }
    /**
     * タイプ
     */
    public Byte getType() {
        return type;
    }
    /**
     * タイプ
     */
    public void setNoOrName(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserSelectQueryDTO [noOrName=" + noOrName + ", type=" + type
                + ", offset=" + offset + ", limit=" + limit + "]";
    }

}
