package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.entity.Article;
import kr.co.unisem.vms.filter.ArticleFilter;
import kr.co.unisem.vms.repository.ArticleRepository;
import kr.co.unisem.vms.repository.CommonRepository;
import kr.co.unisem.vms.repository.TestRepository;
import kr.co.unisem.vms.vo.DbResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommonRepository commonRepository;

    @RequestMapping(value = "article", method = { RequestMethod.GET, RequestMethod.POST })
    public String displayAllArticles(@ModelAttribute ArticleFilter filter, Model model) {
        log.info(filter.toString());
        model.addAttribute("filter", filter);
        return "test/article";
    }

    @GetMapping("articles")
    public ResponseEntity<Object> listArticles(@ModelAttribute("paging") ArticleFilter paging) {
        List<Article> list = articleRepository.getArticlesPaged(paging);
        log.info(paging.toString());

        if (!paging.isFastPaging()) {
            int total = commonRepository.selectTotalRows();
            DbResult rs = new DbResult("", list, total);
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


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

//    @GetMapping("articles")
//    public ResponseEntity<List<Article>> getAllArticles() {
//        List<Article> articles = testRepository.findAll();
//        return new ResponseEntity<>(articles, HttpStatus.OK);
//    }


    // ============================================================================================
//    private EnumMapper enumMapper;
//
//    public ArticleController(EnumMapper enumMapper) {
//        this.enumMapper = enumMapper;
//    }
//    @GetMapping("mapper")
//    public Map<String, List<EnumValue>> getMapper() {
//        return enumMapper.getAll();
////        return enumMapper.get("commissionCutting");
//    }
//
//    @GetMapping("models")
//    public List<EnumModel> getModel() {
//        return Arrays
//                .stream(EnumContract.CommissionType.class.getEnumConstants())
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/enum")
//    public Map<String, Object> getEnum() {
//        Map<String, Object> enums = new LinkedHashMap<>();
//
//        Class commissionType = EnumContract.CommissionType.class;
//        Class commissionCutting = EnumContract.CommissionCutting.class;
//
//        enums.put("commissionType", commissionType.getEnumConstants());
//        enums.put("commissionCutting", commissionCutting.getEnumConstants());
//        return enums;
//    }

//
//    @GetMapping("/value")
//    public Map<String, List<EnumValue>> getEnumValue() {
//        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();
//
//        enumValues.put("commissionType", toEnumValues(EnumContract.CommissionType.class));
//        enumValues.put("commissionCutting", toEnumValues(EnumContract.CommissionCutting.class));
//
//        return enumValues;
//    }
//
//    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e){
//        return Arrays
//                .stream(e.getEnumConstants())
//                .map(EnumValue::new)
//                .collect(Collectors.toList());
//    }


    // ============================================================================================


//    public String hello(Model model) {
//@RequestMapping(value = "articles", method = { RequestMethod.GET, RequestMethod.POST })
//public String displayAllArticles(@ModelAttribute ArticleFilter filter, Model model) {
//    log.info(filter.toString());
//        log.info("from:{}, to:{}, isFastPaging={}", filter.getStartDate(), filter.getEndDate(), filter.isFastPaging());
//        PagedLogFilter filter = new PagedLogFilter();
//        filter.setStartDate("2018-12-04 00:00");
//        filter.setEndDate("2018-12-05 23:59");
//        modelAndView.addObject("filter", filter);
//    model.addAttribute("filter", filter);
//        EnumRiskLevel.RiskLevelType.
//        model.addAttribute("orgTypes", EnumOrg.OrgType.values());
//        mv.addObject("filter", filter);
//        modelAndView.addObject("var1", "var1");
//        modelAndView.addObject(Arrays.asList("var2", "var3", "var4"));
//        modelAndView.getModel().put("var5", "var5");
//        modelAndView.getModelMap().addAttribute("var6", "var6");
//        modelAndView.getModelMap().addAttribute(Arrays.asList("var7", "var8", "var9"));

//        model.addAttribute("attribute1", "attributeValue1");

//    return "articles/articles";
//}
//        //model.addAttribute("name", name);
//        return "articles";
//    }
//
//
//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }
//    public String displayAllArticles(Model mv) {
//        return "templates/articles";
//    }
//    public ModelAndView displayAllArticles() {
//        ModelAndView mv = new ModelAndView();
//
//        PagedLogFilter filter = new PagedLogFilter();
//        filter.setStartDate("2018-12-04 0:00");
//        filter.setEndDate("2018-12-05 23:59");
//
//        mv.addObject("filter", filter);
//
//        mv.addObject("userArticles", articleRepository.getAllArticles());
//        mv.setViewName("articles");
//        return mv;
//    }


//    @GetMapping("articles/paged")
//    public ModelAndView articles2(ModelAndView mv) {
//        ModelAndView mv = new ModelAndView("articles");
//        mv.setViewName("articles");
//        return mv;
//    }



}




// https://jsonobject.tistory.com/449
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JpaDemoApplicationTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void createUser() {
//
//        User user = new User();
//        user.setName("test");
//        user.setEmail("test@gmail.com");
//        user.setDate(LocalDateTime.now());
//        userRepository.save(user);
//    }
//}