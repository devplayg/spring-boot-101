package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
}