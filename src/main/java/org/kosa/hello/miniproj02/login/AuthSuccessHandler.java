package org.kosa.hello.miniproj02.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosa.hello.miniproj02.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private LoginMapper loginMapper;
	private final String LOGIN_ATTEMPT_SESSION_KEY = "loginAttempt";

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication //로그인한 사용자 정보가 있는 객체
    		) throws IOException, ServletException {

//		//로그인 한 마지막 시간 수정
//		memberMapper.updateMemberLastLogin(authentication.getName());
//		//로그인 실패시 카운트를 초기화 한다
//		memberMapper.loginCountClear(authentication.getName());

		System.out.println("authentication ->" + authentication);

		//성공시 이동할 주소
		//설정(onfig)에서 defaultSuccessUrl("/") 으로 설정한 것 보다 아래의 코드로 설정한 것이 변경되서 동작함
        setDefaultTargetUrl("/");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
