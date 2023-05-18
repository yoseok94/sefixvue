package org.fix.sefixvue.config;

import lombok.extern.slf4j.Slf4j;
import org.fix.sefixvue.common.security.CustomAccessDeniedHandler;
import org.fix.sefixvue.common.security.CustomLoginSuccessHandler;
import org.fix.sefixvue.common.security.CustomNoOpPasswordEncoder;
import org.fix.sefixvue.common.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;  //추가

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config ...");

        http.authorizeRequests().antMatchers("/").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmcheck").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmcheck2").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmlist").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmorder").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmmember").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmenroll").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/hrm/hrmup").hasRole("MEMBER");

        http.authorizeRequests().antMatchers("/accounting/salarylist").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/adlist").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/sliplist").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/salarywrite").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/adwrite").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/sliptwrite").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/salarymodify").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/admodify").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/slipmodify").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/accounting/payslib/**").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");

        http.authorizeRequests().antMatchers("/notice/list").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/notice/{noticeno}").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/notice").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/event/list").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/event/{eventno}").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/event").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/dept/list").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/dept/{deptno}").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/dept").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/product/list").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/product/{productno}").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/product").hasRole("ADMIN");

        http.authorizeRequests().antMatchers("/management/deptmove").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/management/personaldetails").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/management/accessrestriction").hasAnyRole("EMPLOYEE");
        http.authorizeRequests().antMatchers("/management/deptapplist").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/management/movinghistory").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/management/appdetailscheck").hasAnyRole("EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/management/movedetailscheck").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/management/writereasonapp").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/management/writereasonmove").hasAnyRole("EXECUTIVES", "ADMIN");

        http.authorizeRequests().antMatchers("/business/planlist").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/business/disbursementlist").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/performancelist").hasAnyRole("EMPLOYEE", "EXECUTIVES", "ADMIN");
        http.authorizeRequests().antMatchers("/business/disbursementadlist").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/business/planadd").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/disbursementadd").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/planedit").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/disbursementedit").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/disbursementdel").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/plandel").hasAnyRole("EMPLOYEE", "EXECUTIVES");
        http.authorizeRequests().antMatchers("/business/disbursementsend").hasRole("ADMIN");

        // 폼 기반 인증 기능을 사용한다.
        http.formLogin().loginPage("/login").successHandler(authenticationSuccessHandler());

        // 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화한다.
        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 스프링 시큐리티가 원하는 결과를 반환하는 쿼리를 작성한다.
        //인증할 때 필요한 쿼리
        //String query1 = "SELECT emp_id, emp_pw FROM employee WHERE emp_id = ?";
        //권한을 확인할 때 필요한 쿼리
        //String query2 ="SELECT emp_id, emp_level FROM employee WHERE emp_id = ?";

        auth.userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomNoOpPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }
}
