package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

//    @Query(value = "delete from MemberRole r where r.memberID = ?1", nativeQuery = true)
//    void deleteRoleListByMemberID(long memberID);
}