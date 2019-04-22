package kr.co.unisem.vms.code;

import kr.co.unisem.vms.vo.EnumModel;

import javax.persistence.*;
import javax.persistence.EnumType;

public class EnumContract {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // enum의 key를 DB에 저장하기 위해, 없을 경우 enum의 디폴트값인 숫자가 들어간다.
    private CommissionType commissionType; // 수수료 타입 (예: 퍼센테이지, 금액)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommissionCutting commissionCutting; // 수수료 절삭 (예: 반올림, 올림, 버림)

    public enum CommissionType implements EnumModel {
        PERCENT("퍼센트"),
        MONEY("금액");

        private String value;

        CommissionType(String value) {
            this.value = value;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public enum CommissionCutting implements EnumModel {
        ROUND("반올림"),
        CEIL("올림"),
        FLOOR("버림");

        private String value;

        CommissionCutting(String value) {
            this.value = value;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getValue() {
            return value;
        }
    }
//    public EnumContract() {}
//    public EnumContract(CommissionType commissionType, CommissionCutting commissionCutting) {
//        this.commissionType = commissionType;
//        this.commissionCutting = commissionCutting;
//    }

}