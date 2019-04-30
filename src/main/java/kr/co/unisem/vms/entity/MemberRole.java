package kr.co.unisem.vms.entity;

import kr.co.unisem.vms.code.EnumRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mbr_role")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleID;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumRole.Role role;

    @Column
    @CreationTimestamp
    private LocalDateTime created;
}
