package org.kosa.hello.miniproj02.admin.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/list")
    @ResponseBody
    public List<UserVO> list(){
        return null;
    }
}
