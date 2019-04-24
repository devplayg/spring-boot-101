package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository   {
    Member findByUsername(String username);
}