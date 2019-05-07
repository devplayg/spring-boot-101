package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.Member;
import kr.co.unisem.vms.entity.MemberRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
//    void deleteMemberRolesByMemberID(long memberID);
//    @Transactional
//    @Modifying
//    @Query("delete from mbr_role c where c.member_id in :ids")
//    void deleteRolesByMember(@Param("ids") Member member);

}