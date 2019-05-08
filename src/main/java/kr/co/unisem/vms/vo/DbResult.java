package kr.co.unisem.vms.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DbResult {
    private int total;
    private List rows;
    private String error;

    public DbResult(String error) {
        this.error = error;
    }

    public DbResult(String error, List rows) {
        this.error = error;
        this.rows = rows;
        this.total = (rows == null) ? 0 : rows.size();
    }

    public DbResult(String error, int total) {
        this.error = error;
        this.total = total; // affected rows
    }

    public DbResult(String error, List rows, int total) {
        this.error = error;
        this.rows = rows;
        this.total = total;
    }
}