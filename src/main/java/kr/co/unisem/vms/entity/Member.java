package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javassist.runtime.Inner;
import kr.co.unisem.vms.code.EnumOrg;
import kr.co.unisem.vms.code.EnumRole;
import kr.co.unisem.vms.vo.EnumValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mbr_member")
@ToString
@NoArgsConstructor
@Slf4j
// @JsonIgnoreProperties(value = {"created", "updated"},
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberID;

    @Column(name = "username", unique = true)
    @NotBlank
    private String username;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String email;

    @Column(name = "name")
    @NotBlank
    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    @JoinColumn(name = "member_id")
    private MemberPassword password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "member")
    private List<MemberRole> roleList;

    @Column(columnDefinition = "TINYINT(3)")
    private boolean enabled;

    public Member(int memberID) {
        this.memberID = memberID;
    }

    @Transient
    public List<EnumRole.Role> getRoleEnumList() {
        List<EnumRole.Role> list = new ArrayList<>();
        for (MemberRole role : roleList) {
            list.add(role.getRole());
        }
        return list;
    }
}
