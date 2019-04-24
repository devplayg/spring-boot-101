package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Article;
import kr.co.unisem.vms.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = testRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
