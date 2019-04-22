package kr.co.unisem.vms.config;

import kr.co.unisem.vms.code.EnumOrg;
import kr.co.unisem.vms.code.EnumRiskLevel;
import kr.co.unisem.vms.vo.EnumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CodeConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("orgType", EnumOrg.OrgType.class);
        enumMapper.put("riskLevel", EnumRiskLevel.RiskLevel.class);
        return enumMapper;
    }
}