package com.devplayg.vms.filter;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Paging implements Serializable {
    private static final long serialVersionUID = 1L;

    private int offset;
    private int limit;
    private String sort;
    private String order;
    private boolean fastPaging;

    public void setOffset(int offset) {
        this.offset = (offset > 0) ? offset : 0;
    }

    public void setLimit(int limit) {
        this.limit = (limit > 100 || limit < 0) ? 10 : limit;
    }
}
