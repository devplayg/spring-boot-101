package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {
    Member findByUsername(String username);
}