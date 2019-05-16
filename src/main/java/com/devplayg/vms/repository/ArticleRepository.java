package com.devplayg.vms.repository;

import com.devplayg.vms.entity.Article;
import com.devplayg.vms.filter.ArticleFilter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleRepository {
    List<Article> getAllArticles();

    List<Article> getArticlesPaged(ArticleFilter filter);
}
