package kr.co.unisem.vms.controller;

import kr.co.unisem.vms.code.EnumContract;
import kr.co.unisem.vms.vo.EnumMapper;
import kr.co.unisem.vms.vo.EnumModel;
import kr.co.unisem.vms.vo.EnumValue;
import kr.co.unisem.vms.entity.Article;
import kr.co.unisem.vms.repository.ArticleRepository;
import kr.co.unisem.vms.repository.CommonRepository;
import kr.co.unisem.vms.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("app")
@Slf4j
@RestController
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

    // ============================================================================================
    private EnumMapper enumMapper;

    public ArticleController(EnumMapper enumMapper) {
        this.enumMapper = enumMapper;
    }
    @GetMapping("mapper")
    public Map<String, List<EnumValue>> getMapper() {
        return enumMapper.getAll();
//        return enumMapper.get("commissionCutting");
    }
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


    @RequestMapping(value = "articles", method = { RequestMethod.GET, RequestMethod.POST })
    public String displayAllArticles(@ModelAttribute("form-article") PagedLogFilter filter, Model model) {
        log.info(filter.toString());
//        log.info("from:{}, to:{}, isFastPaging={}", filter.getStartDate(), filter.getEndDate(), filter.isFastPaging());
//        PagedLogFilter filter = new PagedLogFilter();
//        filter.setStartDate("2018-12-04 00:00");
//        filter.setEndDate("2018-12-05 23:59");
//        modelAndView.addObject("filter", filter);
        model.addAttribute("filter", filter);
//        mv.addObject("filter", filter);
//        modelAndView.addObject("var1", "var1");
//        modelAndView.addObject(Arrays.asList("var2", "var3", "var4"));
//        modelAndView.getModel().put("var5", "var5");
//        modelAndView.getModelMap().addAttribute("var6", "var6");
//        modelAndView.getModelMap().addAttribute(Arrays.asList("var7", "var8", "var9"));

//        model.addAttribute("attribute1", "attributeValue1");

        return "articles/articles";
    }
//    public String hello(Model model) {
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

    @GetMapping("articles/list")
    public ResponseEntity<Object> listArticles(@ModelAttribute("paging") PagedLogFilter paging) {
        List<Article> list = articleRepository.getArticlesPaged(paging);
        log.info(paging.toString());

        if (!paging.isFastPaging()) {
            int total = commonRepository.selectTotalRows();
//            log.debug("total = {}", total);
            DbResult rs = new DbResult(list, total);
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


//    private boolean isAjax(HttpServletRequest request) {
//        String requestedWithHeader = request.getHeader("X-Requested-With");
//        return "XMLHttpRequest".equals(requestedWithHeader);
//    }
}
