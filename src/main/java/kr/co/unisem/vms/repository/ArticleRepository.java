package kr.co.unisem.vms.repository;

import kr.co.unisem.vms.entity.Article;
import kr.co.unisem.vms.vo.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleRepository  {
    List<Article> getAllArticles();
    List<Article> getArticlesPaged(Paging paging);
}
