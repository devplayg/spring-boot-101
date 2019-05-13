package com.devplayg.vms.code;

import com.devplayg.vms.vo.EnumModel;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class EnumRiskLevel {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumRiskLevel.RiskLevel riskLevel;

    public enum RiskLevel implements EnumModel {
        RiskLevel1("정상"),
        RiskLevel2("관심"),
        RiskLevel3("주의"),
        RiskLevel4("경계"),
        RiskLevel5("심각");

        private String value;

        RiskLevel(String value) {
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
}
