package kr.co.unisem.vms.vo;

import lombok.*;

@Getter
@Setter
@ToString(callSuper=true)
public class PagedLogFilter extends Paging {
    private String startDate;
    private String endDate;
    private String title;
    private Integer[] riskLevel;

//    public PagedLogFilter(String startDate, String endDate, int offset, int limit, boolean fastPaging) {
//        super(offset, limit, fastPaging);
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
}
