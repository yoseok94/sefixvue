package org.fix.sefixvue.config;


import lombok.AllArgsConstructor;
import org.fix.sefixvue.hrm.model.service.HrmService;
import org.fix.sefixvue.jwt.JwtAuthenticationFilter;
import org.fix.sefixvue.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@AllArgsConstructor
@EnableWebSecurity //security 활성화 어노테이션
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HrmService hrmService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private DataSource dataSource;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {   //유저 정보 가져오는 클래스
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/templates/**");
    }

    //http 관련 인증 설정하기.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll() //모든 메소드 -> 모두 허용
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login");
        http
                .logout()   //logout 관련 설정
                .logoutSuccessUrl("/")  //로그아웃 성공시 리다이렉트주소
                .invalidateHttpSession(true);    //로그 아웃 후 세션 전체 삭제 여부

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);
    }
}
