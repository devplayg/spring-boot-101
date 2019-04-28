package kr.co.unisem.vms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="mbr_role")
@Getter
@Setter
@ToString
public class MemberRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleID;

    @Column(name = "member_id")
    private int memberID;

    private String role;

    private LocalDateTime created;
}
