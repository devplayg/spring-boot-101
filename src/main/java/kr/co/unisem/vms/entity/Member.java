package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(nullable=false, length=100)
    private String email;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private MemberPassword password;

    @Column(name="role")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Collection<MemberRole> role;

    @Column(columnDefinition = "TINYINT(3)")
    private boolean enabled;

    public void setPassword(String password) {
        if (this.password == null) {
            this.password = new MemberPassword(password);
        } else {
            this.password.setPassword(password);
        }
    }

    public void setRole(String role) {
        if (this.role == null) {
            this.role = new ArrayList<MemberRole>();
        }
        this.role.add(new MemberRole(role));
    }
}
