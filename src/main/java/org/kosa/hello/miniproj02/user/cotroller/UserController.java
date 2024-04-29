package org.kosa.hello.miniproj02.user.cotroller;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.HobbyVO;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.hobby.service.HobbyService;
import org.kosa.hello.miniproj02.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final HobbyService hobbyService;

    @GetMapping("/insertForm")
    public String insertForm(Model model) {
        model.addAttribute("hobby", hobbyService.getHobbyList());
        return "/user/insertForm";
    }


    @ResponseBody
    @Transactional
    @PostMapping("/insert")
    public Map<String, Object> insert(@RequestBody UserVO userVO,HttpServletRequest request) {
        int userUpdated = userService.userInsert(userVO);
        int hobbyUpdated = hobbyService.hobbyInsert(userVO.getUser_id(),userVO.getHobby());
        Map<String, Object> map = new HashMap<>();
        if (userUpdated != 0 && hobbyUpdated != 0) {
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "실패");
        }

        return map;
    }

    @Transactional
    @GetMapping("/myPage")
    public String myPage(Model model, Principal principal) {
        UserVO userVO = userService.getUserInfo(principal.getName());
        List<HobbyVO> hobbyList = hobbyService.getHobbyInfo(userVO);
        model.addAttribute("hobbyList", hobbyList);
        model.addAttribute("user", userVO);
        return "/user/read";
    }
}
