package kr.co.unisem.vms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="mbr_password")
@Getter
@Setter
@ToString
public class MemberPassword implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="password_id")
    private int passwordID;

    @Column(name="member_id")
    private int memberID;

    private String password;

    private LocalDateTime created;
}
