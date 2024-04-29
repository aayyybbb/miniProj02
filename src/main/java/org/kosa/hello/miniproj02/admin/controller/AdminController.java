package org.kosa.hello.miniproj02.admin.controller;

import lombok.AllArgsConstructor;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model){
        List<UserVO> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "/admin/list";
    }
}
