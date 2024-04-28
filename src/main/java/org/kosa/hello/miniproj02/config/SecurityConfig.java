package org.kosa.hello.miniproj02.config;

import org.kosa.hello.miniproj02.login.AuthFailureHandler;
import org.kosa.hello.miniproj02.login.AuthSuccessHandler;
import org.kosa.hello.miniproj02.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import java.net.URLEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginService loginService;
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	@Autowired
	private AuthFailureHandler authFailureHandler;

	@Bean
	public BCryptPasswordEncoder encryptPassword() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(loginService).passwordEncoder(encryptPassword());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//		    http.csrf().disable() // CSRF 보호 비활성화
//		        .authorizeRequests()
//		        .anyRequest().permitAll(); // 모든 요청에 대해 접근 허용

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

			http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/login/**", "/user/insertForm", "/user/insert", "/js/**","/css/**","/image/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll()
//			.authenticated()
		.and()
			.formLogin()
			.usernameParameter("userId")
			.passwordParameter("pwd")
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
			.successHandler(authSuccessHandler)
			.failureHandler(authFailureHandler)
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
		.and()
			.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false)
			.expiredUrl("/login/loginForm?error=true&exception=" + URLEncoder.encode("세션이 만료되었습니다. 다시 로그인 해주세요", "UTF-8"))
		;
    }

}
