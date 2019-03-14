package com.djb.ylt.framework.entity;

import java.util.List;

public class PageModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public static int PAGE_SIZE = 10;

    public static String DB_TYPE_MYSQL = "mysql";
    public static String DB_TYPE_ORACLE = "oracle";

    private int startRow = 1;

    private int endRow = 1;

    private int pageSize;

    private int totalItem;
    
    private int pageNum;

    private String DBType = DB_TYPE_MYSQL;

    private List<? extends BaseEntity> list;

    public String getDBType() {
        return DBType;
    }

    public void setDBType(String dBType) {
        DBType = dBType;
    }

    public int getStartRow() {
        // if (DB_TYPE_MYSQL.equals(DBType)) {
        // startRow = startRow - 1;
        // }
        return startRow - 1;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setPageSize(int pageSize) {
		if (pageSize < 0) {
			this.pageSize = PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
    }

	public int getPageSize() {
		// return pageSize = endRow > startRow ? endRow - startRow + 1 :
		// PAGE_SIZE;
		return pageSize;
	}

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public List<? extends BaseEntity> getList() {
        return list;
    }

    public void setList(List<? extends BaseEntity> list) {
        this.list = list;
    }

	/**
	 * 返回pageNum的值
	 * @return int pageNum的值
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置pageNum的值
	 * @param  pageNum pageNum的值
	 */
	public void setPageNum(int pageNum) {
		if (pageNum < 0) {
			this.pageNum = 0;
		} else {
			this.pageNum = pageNum;
		}
	}

}
