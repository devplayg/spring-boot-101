package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.unisem.vms.code.EnumRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mbr_role")
@Getter
@Setter
@ToString(exclude = "member")  // 순환참조 차단
@EqualsAndHashCode(of = {"role"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "roleID")
public class MemberRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleID;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "member_id")
    @JsonManagedReference
    private Member member;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumRole.Role role;

    @Column
    @CreationTimestamp
    private LocalDateTime created;

    @Builder
    public MemberRole(EnumRole.Role role) {
        this.role = role;
    }

    public MemberRole(EnumRole.Role role, LocalDateTime created) {
        this.role = role;
        this.created = created;
    }
}
