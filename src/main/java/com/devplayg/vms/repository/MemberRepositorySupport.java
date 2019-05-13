package com.devplayg.vms.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.devplayg.vms.entity.Member;
import com.devplayg.vms.entity.QMember;
import com.devplayg.vms.entity.QMemberRole;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> find(String name) {
        QMember member = QMember.member;
        QMemberRole roleList = QMemberRole.memberRole;
        return queryFactory
                .selectFrom(member)
                .distinct()
                .leftJoin(member.roleList, roleList).fetchJoin()
                .where(member.name.like("%" + name + "%"))
                .fetch();
    }

}