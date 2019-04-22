package kr.co.unisem.vms.code;

import kr.co.unisem.vms.vo.EnumModel;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class EnumOrg {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumOrg.OrgType orgType;

    public enum OrgType implements EnumModel {
        orgLab("연구소"),
        orgSales("마케팅"),
        orgManage("경영");

        private String value;

        OrgType(String value) {
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

