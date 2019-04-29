package kr.co.unisem.vms.config;

import kr.co.unisem.vms.code.EnumOrg;
import kr.co.unisem.vms.code.EnumRiskLevel;
import kr.co.unisem.vms.code.EnumRole;
import kr.co.unisem.vms.vo.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@SpringBootApplication
public class CodeConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("orgType", EnumOrg.OrgType.class);
        enumMapper.put("riskLevel", EnumRiskLevel.RiskLevel.class);
        enumMapper.put("role", EnumRole.Role.class);
        return enumMapper;
    }

//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.KOREA);
//        return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
}