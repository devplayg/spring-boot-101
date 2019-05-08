package kr.co.unisem.vms.code;

import kr.co.unisem.vms.vo.EnumModel;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class EnumRole {
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EnumRole.Role role;

    public enum Role implements EnumModel {
        Admin("administrator"),
        Sheriff("sheriff"),
        User("normal user");

        private String value;

        Role(String value) {
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
