package kr.co.unisem.vms.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper=true)
public class PagedLogFilter extends Paging {
    private String startDate;
    private String endDate;
    private String title;
    private String[] riskLevel;
    private List<String> org;
    private Integer[] category;

//    public PagedLogFilter(String startDate, String endDate, int offset, int limit, boolean fastPaging) {
//        super(offset, limit, fastPaging);
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
}
