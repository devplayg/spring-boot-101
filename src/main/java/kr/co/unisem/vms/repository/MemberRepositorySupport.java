package kr.co.unisem.vms.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.unisem.vms.entity.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.unisem.vms.entity.QMember.member;


//public interface MemberRepositorySupport {
//    List<Member> findByNameLike(String name);
//}

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> findByName(String name) {
        return queryFactory
                .selectFrom(member)
                .leftJoin(member.roleList, r)
                .where(member.name.like("%"+name+"%"))
                .fetch();
    }

}