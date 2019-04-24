package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name="mbr_member")
@NoArgsConstructor
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long memberID;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    @JsonIgnore
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="name")
    private String name;

    @Column(name="enabled")
    private short enabled;
}
