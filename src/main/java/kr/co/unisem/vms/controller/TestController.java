package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Article;
import kr.co.unisem.vms.repository.TestRepository;
import kr.co.unisem.vms.vo.DbResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("article2/{id}")
    public ResponseEntity<DbResult> getArticle2(@PathVariable("id") long articleID) {
        Optional<Article> article = testRepository.findById(articleID);
        if (article.isPresent()) {
            return ResponseEntity.ok(new DbResult("", Arrays.asList(article.get())));
        } else {
            return ResponseEntity.ok(new DbResult("", null));
        }
    }

    @GetMapping("article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") long articleID) {
        Optional<Article> article = testRepository.findById(articleID);
        return ResponseEntity.ok(article.get());
//        if (article.isPresent()) {
//            return ResponseEntity.ok(article.get());
//        } else {
//            return (List<TestController>) EMPTY_LIST;
//        }

//        Article article = testRepository.getOne(articleID);
//
//        Article a = new Article();
//        a.setTitle(article.getTitle());
//        log.info("### getTitle: {}", article.getTitle());
//        return ResponseEntity.ok(a);



//        List<Article> list = testRepository.findAll();
//        if(article.isPresent()){
//            return ResponseEntity.ok(groceryOptional.get());
//        }else {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(MessageUtil.parse(MSG_404_GROCERY, id + ""));
//        }
//        article.isp
//        return new ResponseEntity<Article>(article, HttpStatus.OK);
//        Article article = testRepository.getOne(articleID);
//        return new ResponseEntity<>(article, HttpStatus.OK);

//        return ResponseEntity.accepted().body(book);
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = testRepository.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }



}
