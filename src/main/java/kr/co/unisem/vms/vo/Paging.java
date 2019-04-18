package kr.co.unisem.vms.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Paging implements Serializable {
    private static final long serialVersionUID = 1L;

    private int offset;
    private int limit;
    private String sort;
    private String order;
    private boolean fastPaging;

    public Paging() {
        this.offset = 0;
        this.limit = 7;
        this.fastPaging = true;
    }

    public void setOffset(int offset) {
        this.offset = (offset > 0) ? offset : 0;
    }
    public void setLimit(int limit) {
        this.limit = (limit > 100  || limit < 0) ? 10 : limit;
    }
}
