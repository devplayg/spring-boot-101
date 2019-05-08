package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.unisem.vms.code.EnumRole;
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

    @OneToMany(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {
                    CascadeType.PERSIST, // Child entities이 삭제 되도록 함
                    CascadeType.MERGE // Child entities를 Insert할 때, Parent ID를 기록한 후 Insert 함
            },
            mappedBy = "member")
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
