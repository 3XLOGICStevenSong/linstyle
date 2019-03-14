package cn.com.dbridge.lifecare.framework.base;

import lombok.Data;

@Data
public class PageListDTO {
    /**
     * 总记录数
     */
    private Long count;
    /**
     * 列表
     */
    private Object list;
    
    public PageListDTO(Long count, Object list) {
        super();
        this.count = count;
        this.list = list;
    }
}
