package kr.co.unisem.vms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VmsApplication {
    private static final Logger logger = LoggerFactory.getLogger(VmsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(VmsApplication.class, args);
    }



//    // 테스트 코드
//    @Autowired
//    JdbcTemplate jdbcTemplate;

//    // 테스트 코드
//    @PostConstruct
//    public void init() {
//        jdbcTemplate.execute("DROP TABLE IF EXISTS customers ");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
//    }

}
