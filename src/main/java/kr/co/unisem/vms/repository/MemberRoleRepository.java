package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
}