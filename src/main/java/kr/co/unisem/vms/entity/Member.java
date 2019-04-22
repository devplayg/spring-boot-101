package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
//@Setter
@Table(name="mbr_member")
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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
