package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Article, Long> {
}