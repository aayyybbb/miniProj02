package org.kosa.hello.miniproj02.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/login/loginForm";
    }
    @GetMapping("/logout")
   	public String logout(HttpServletRequest request) {
   		HttpSession session = request.getSession(false);
   		if (session != null) {
   			session.invalidate();
   		}
   		return "redirect:/";
   	}
}
