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

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication
    		) throws IOException, ServletException {

		loginMapper.setLoginTime(authentication.getName());
		loginMapper.unLock(authentication.getName());
		System.out.println("authentication ->" + authentication);

        setDefaultTargetUrl("/");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
