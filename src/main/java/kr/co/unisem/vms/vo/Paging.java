package kr.co.unisem.vms.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paging implements Serializable {
    private static final long serialVersionUID = 1L;

    private int offset;
    private int limit;
    private String sort;
    private String order;
    private boolean fastPaging;

//    @Builder
//    public Paging(int offset, int limit, boolean fastPaging) {
//        this.offset = offset;
//        this.limit = limit;
//        this.fastPaging = fastPaging;
//    }

    public void setOffset(int offset) {
        this.offset = (offset > 0) ? offset : 0;
    }
    public void setLimit(int limit) {
        this.limit = (limit > 100  || limit < 0) ? 10 : limit;
    }
}
