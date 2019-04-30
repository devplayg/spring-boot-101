package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
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
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    @JoinColumn(name = "member_id")
    private MemberPassword password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberRole> roleList;

    @Column(columnDefinition = "TINYINT(3)")
    private boolean enabled;

    public Member(int memberID) {
        this.memberID = memberID;
    }
}
