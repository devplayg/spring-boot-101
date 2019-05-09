package kr.co.unisem.vms.config;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Provider;

@Configuration
//@EnableTransactionManagement
public class JpaConfig {
    @Getter
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory JpaConfig() {
        return new JPAQueryFactory(entityManager);
    }
}



//
//public class JPAQueryFactory implements JPQLQueryFactory  {
//
//    @Nullable
//    private final JPQLTemplates templates;
//
//    private final Provider<EntityManager> entityManager;
//
//    public JPAQueryFactory(final EntityManager entityManager) {
//        this.entityManager = new Provider<EntityManager>() {
//            @Override
//            public EntityManager get() {
//                return entityManager;
//            }
//        };
//        this.templates = null;
//    }
//
//    public JPAQueryFactory(JPQLTemplates templates, final EntityManager entityManager) {
//        this.entityManager = new Provider<EntityManager>() {
//            @Override
//            public EntityManager get() {
//                return entityManager;
//            }
//        };
//        this.templates = templates;
//    }
//
//    public JPAQueryFactory(Provider<EntityManager> entityManager) {
//        this.entityManager = entityManager;
//        this.templates = null;
//    }
//
//    public JPAQueryFactory(JPQLTemplates templates, Provider<EntityManager> entityManager) {
//        this.entityManager = entityManager;
//        this.templates = templates;
//    }
//
//    @Override
//    public JPADeleteClause delete(EntityPath<?> path) {
//        if (templates != null) {
//            return new JPADeleteClause(entityManager.get(), path, templates);
//        } else {
//            return new JPADeleteClause(entityManager.get(), path);
//        }
//    }
//
//    @Override
//    public <T> JPAQuery<T> select(Expression<T> expr) {
//        return query().select(expr);
//    }
//
//    @Override
//    public JPAQuery<Tuple> select(Expression<?>... exprs) {
//        return query().select(exprs);
//    }
//
//    @Override
//    public <T> JPAQuery<T> selectDistinct(Expression<T> expr) {
//        return select(expr).distinct();
//    }
//
//    @Override
//    public JPAQuery<Tuple> selectDistinct(Expression<?>... exprs) {
//        return select(exprs).distinct();
//    }
//
//    @Override
//    public JPAQuery<Integer> selectOne() {
//        return select(Expressions.ONE);
//    }
//
//    @Override
//    public JPAQuery<Integer> selectZero() {
//        return select(Expressions.ZERO);
//    }
//
//    @Override
//    public <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
//        return select(from).from(from);
//    }
//
//    @Override
//    public JPAQuery<?> from(EntityPath<?> from) {
//        return query().from(from);
//    }
//
//    @Override
//    public JPAQuery<?> from(EntityPath<?>... from) {
//        return query().from(from);
//    }
//
//    @Override
//    public JPAUpdateClause update(EntityPath<?> path) {
//        if (templates != null) {
//            return new JPAUpdateClause(entityManager.get(), path, templates);
//        } else {
//            return new JPAUpdateClause(entityManager.get(), path);
//        }
//    }
//
//    @Override
//    public JPAQuery<?> query() {
//        if (templates != null) {
//            return new JPAQuery<Void>(entityManager.get(), templates);
//        } else {
//            return new JPAQuery<Void>(entityManager.get());
//        }
//    }
//
//}