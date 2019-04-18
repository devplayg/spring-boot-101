package kr.co.unisem.vms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

// 희주대리
@Configuration

// 웹 보안 활성화
@EnableWebSecurity

// 어노테이션을 사용하여 어노테이션 기반 보안을 적용
@EnableGlobalMethodSecurity(securedEnabled=true)

// 로깅
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberDetailsService memberDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("### Security configure");


        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
            .authorizeRequests()

                // 아래 URL 패턴에 매칭되면
                .antMatchers("/app/secure/**")
                    .hasAnyRole("ADMIN","USER")
                .antMatchers("/", "/login/**",  "/plugins/**", "/css/**", "/images/**", "/js/**", "/resources/**") // URL 패턴들
                    // 모든 요청을 허용함
                    .permitAll() // 이 URL 패턴들은 인증요구 없이 허용
                // 그외 요청은 인증을 요구함
                .anyRequest().authenticated() // 그 외 모든 요청은 인증을 요구함
                .and()
            .formLogin()// 사용자 로그인
//                 https://docs.spring.io/spring-security/site/docs/current/guides/html5/form-javaconfig.html
                // 사용자 로그인 페이지
                .loginPage("/app/login")

                // 로그인 절차 진행
                .loginProcessingUrl("/app-login")

                // 사용자 아이디 파라메터
                .usernameParameter("app_username")

                // 사용자 비밀번호 파라메터
                .passwordParameter("app_password")
                //.successForwardUrl("/members")
                .defaultSuccessUrl("/app/articles")
                .permitAll()
                .and()
            .logout()
                // 로그아웃 URL
                .logoutUrl("/app-logout")

                // 로그아웃 성공 시 리다이렉트 될 URL
                .logoutSuccessUrl("/app/login")

                // 쿠키 삭제
                .deleteCookies("JSESSIONID")

                // 세션 제거
                .invalidateHttpSession(true)

                .permitAll()
                .and()

                .addFilterBefore(filter, CsrfFilter.class)
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(memberDetailsService).passwordEncoder(encoder);
    }


    //        http
//            .authorizeRequests()
//                .antMatchers("/", "/oauth2/**", "/login/**",  "/css/**", "/images/**", "/js/**", "/console/**").permitAll()
//                .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
//                .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
//                .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
//                .anyRequest().authenticated()
//            .and()
//                .oauth2Login()
//                .defaultSuccessUrl("/loginSuccess")
//                .failureUrl("/loginFailure")
//            .and()
//                .headers().frameOptions().disable()
//            .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//            .and()
//                .formLogin()
//                .successForwardUrl("/board/list")
//            .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//            .and()
//                .addFilterBefore(filter, CsrfFilter.class)
//                .csrf().disable();
}