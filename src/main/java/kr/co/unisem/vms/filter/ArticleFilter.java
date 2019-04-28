package kr.co.unisem.vms.filter;

import kr.co.unisem.vms.filter.Paging;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper=true)
public class ArticleFilter extends Paging {
    private String startDate;
    private String endDate;
    private String title;
    private String[] riskLevel;
    private List<String> org;
    private List<Integer> category;
}
