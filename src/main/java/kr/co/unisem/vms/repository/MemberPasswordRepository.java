package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.MemberPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories
public interface MemberPasswordRepository extends JpaRepository<MemberPassword, Long> {
}
