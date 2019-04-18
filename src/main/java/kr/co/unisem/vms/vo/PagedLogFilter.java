package kr.co.unisem.vms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PagedLogFilter extends Paging {

    @Column(name="start_date")
//    @JsonProperty("start_date")
    private String startDate;

//    @Column(name="end_date")
    @JsonProperty("end_date")
    private String endDate;

    private String title;

    @Override
    public String toString() {
        return String.format("start_date=%s, end_date=%s, title=%s", this.startDate, this.endDate, this.title) + "&" + super.toString();
    }
}
