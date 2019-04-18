package kr.co.unisem.vms.vo;

import kr.co.unisem.vms.vo.Paging;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedLogFilter extends Paging {
    private String StartDate;
    private String EndDate;
}
