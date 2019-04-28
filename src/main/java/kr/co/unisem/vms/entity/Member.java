package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="mbr_member")
@ToString
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private int memberID;

    @Column(name="username", unique = true)
    private String username;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private MemberPassword password;

    @Column(name="role")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Collection<MemberRole> role;

    @Column(name="name")
    private String name;

    @Column(name="enabled", columnDefinition = "TINYINT(3)")
    private boolean enabled;
}
