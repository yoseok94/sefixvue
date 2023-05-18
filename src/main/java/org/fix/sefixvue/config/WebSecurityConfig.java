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
                //                .antMatchers("/members/test").hasRole("MEMBER") // 해당 메소드는 MEMBER 권한을 가진 회원만 허용
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/");
        http
                .logout()   //logout 관련 설정
                .logoutSuccessUrl("/")  //로그아웃 성공시 리다이렉트주소
                .invalidateHttpSession(true);    //로그 아웃 후 세션 전체 삭제 여부

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
//        http.authorizeRequests()    //접근에 대한 인증 설정
////                .antMatchers("/","/user/signup","/loginhome","/member/login", "/member/enroll").permitAll()    //누구나 접근 가능
//
//                .antMatchers("/**").permitAll()    //누구나 접근 가능
//
////                .antMatchers("/mypage", "/mypage/popupU", "/mypage/popupD").hasRole("MEMBER")    //member, admin만 접근가능
////                .antMatchers("/admin").hasRole("ADMIN") //amdin만 접근가능
//                .anyRequest().permitAll()   //권한의종류 관계없이 권한 있어야 접근가능
//
//                .and()
//                .formLogin()    //로그인 관한 설정
//                .loginPage("/member/login")    //로그인 페이지 링크
//                .defaultSuccessUrl("/mypage")     //로그인 성공 후 리다이렉트 주소
//
//                .and()
//                .logout()   //logout 관련 설정
//               // .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) => 필요시 페이지 생성해야함.
//                .logoutSuccessUrl("/")  //로그아웃 성공시 리다이렉트주소
//                .invalidateHttpSession(true)    //로그 아웃 후 세션 전체 삭제 여부
//
//                .and()
//                .csrf()
//                .ignoringAntMatchers("/h2-console/**")
//                .ignoringAntMatchers("/post/**")
//                .ignoringAntMatchers("/admin/**")
//                .ignoringAntMatchers("/video_board/**")
//
//                .and()
//                .csrf().disable()
//
////                .and()
////                .exceptionHandling()
////                .accessDeniedPage("/error")   => 이후 페이지 생성예정
//
////                .and()
////                .oauth2Login()
////                .userInfoEndpoint()
////                .userService(customAutowireConfigurer)    => 소셜로그인시 필요.
//        ;
//
//        http.headers().frameOptions().disable(); //x-frame-options를 비활성화하는 설정 -> 보안이슈 발생가능
//    }
//
//    //로그인할 때 필요한 정보 가져오는 곳
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//
//}
    }
}
