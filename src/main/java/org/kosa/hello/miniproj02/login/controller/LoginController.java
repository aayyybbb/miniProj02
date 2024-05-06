package org.kosa.hello.miniproj02.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/loginForm")
    public String loginForm(Model model,
							@RequestParam(value = "error", required = false) String error,
							@RequestParam(value = "exception", required = false) String exception) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
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
