package kr.co.unisem.vms.config;

import kr.co.unisem.vms.code.EnumContract;
import kr.co.unisem.vms.vo.EnumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("commissionType", EnumContract.CommissionType.class);
        enumMapper.put("commissionCutting", EnumContract.CommissionCutting.class);
        return enumMapper;
    }
}