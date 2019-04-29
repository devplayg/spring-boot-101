package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mbr_member")
@ToString
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberID;

    @Column(name = "username", unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    @JoinColumn(name = "member_id")
    private MemberPassword password = new MemberPassword();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberRole> roleList = new ArrayList<>();

//    @Transient
//    private List<String> roles;

    @Column(columnDefinition = "TINYINT(3)")
    private boolean enabled;


    public void setPassword(String password) {
        this.password.setPassword(password);
    }
//    public void setRole(List<MemberRole> role) {
//        this.role = role;
//    }

//    public void setRole(String role) {
//        this.role.add(new MemberRole(role));
//    }
//
//    public void updateRoles() {
//        for (String r : this.roles) {
//            this.role.add(new MemberRole(r));
//        }
//    }



//    public void setRole(String role) {
//        this.role.add(new MemberRole(role));
//    }

//    public void setPassword(String password) {
//        if (this.password == null) {
//            this.password = new MemberPassword(password);
//        } else {
//            this.password.setPassword(password);
//        }
//    }

//    public void setRole(String role) {
//        if (this.role == null) {
//            this.role = new ArrayList<MemberRole>();
//        }
//        this.role.add(new MemberRole(role));
//    }

//
//
//    public void addToRole(MemberRole role) {
//        role.setMember(this);
//        this.role.add(role);
//    }
//
//    public void addToPassword(MemberPassword pw) {
//        pw.setMember(this);
//        this.password = pw;
//    }
}
