package kr.co.unisem.vms.vo;

import lombok.Getter;

import java.util.List;

@Getter
public class DbResult {
    private int total;
    private List rows;

    public DbResult(List rows, int total) {
        this.rows = rows;
        this.total = total;
    }
}
