package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Article;
import kr.co.unisem.vms.repository.CommonRepository;
import kr.co.unisem.vms.vo.DbResult;
import kr.co.unisem.vms.vo.PagedLogFilter;
import kr.co.unisem.vms.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("app")
@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommonRepository commonRepository;

    @GetMapping("articles/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = articleRepository.getAllArticles();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("articles")
    public ModelAndView displayAllArticles() {
        ModelAndView mv = new ModelAndView();

        PagedLogFilter filter = new PagedLogFilter();
        filter.setStartDate("2018-12-04 0:00");
        filter.setEndDate("2018-12-05 23:59");

        mv.addObject("filter", filter);

        mv.addObject("userArticles", articleRepository.getAllArticles());
        mv.setViewName("articles");
        return mv;
    }
//    public String displayAllArticles(ModelAndView mv) {
//        return "/templates/articles";
//
//    }

//    @GetMapping("articles/paged")
//    public ModelAndView articles2(ModelAndView mv) {
//        ModelAndView mv = new ModelAndView("articles");
//        mv.setViewName("articles");
//        return mv;
//    }

    @GetMapping("/articles/list")
    public ResponseEntity<Object> listArticles(@ModelAttribute("paging") PagedLogFilter paging) {
        List<Article> list = articleRepository.getArticlesPaged(paging);

        log.debug("fastPaging={}, order by {} {}, limit {}, {}", paging.isFastPaging(), paging.getSort(), paging.getOrder(), paging.getOffset(), paging.getLimit());
        if (!paging.isFastPaging()) {
            int total = commonRepository.selectTotalRows();
            log.debug("total = {}", total);
            DbResult rs = new DbResult(list, total);
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    public String listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
//
//        logger.info(cri.toString());
//
//        model.addAttribute("list", service.listCriteria(cri));  // 게시판의 글 리스트
//        PageMaker pageMaker = new PageMaker();
//        pageMaker.setCri(cri);
//        pageMaker.setTotalCount(service.listCountCriteria(cri));
//
//        model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
//
//        return "/samples/board/listPage";
//    }


//    private boolean isAjax(HttpServletRequest request) {
//        String requestedWithHeader = request.getHeader("X-Requested-With");
//        return "XMLHttpRequest".equals(requestedWithHeader);
//    }
}
