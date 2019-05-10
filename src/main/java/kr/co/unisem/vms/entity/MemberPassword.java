package kr.co.unisem.vms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="mbr_password")
@Getter
@Setter
@ToString(exclude = "member") // 순환참조 차단
public class MemberPassword implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="password_id")
    private long passwordID;

    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String salt;

    @Column
    @CreationTimestamp
    private LocalDateTime created;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated;
}
