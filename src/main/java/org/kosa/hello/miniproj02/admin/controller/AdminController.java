package org.kosa.hello.miniproj02.admin.controller;

import lombok.AllArgsConstructor;
import org.kosa.hello.miniproj02.entity.HobbyVO;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.hobby.service.HobbyService;
import org.kosa.hello.miniproj02.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final HobbyService hobbyService;
    private final AdminService adminService;

    @GetMapping("/list")
    public String list(Model model) {
        List<UserVO> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "/admin/list";
    }

    @GetMapping("/read/{user_id}")
    public String myPage(Model model, @PathVariable String user_id, Principal principal) {
        UserVO userVO = userService.getUserInfo(user_id);
        List<HobbyVO> hobbyList = hobbyService.getHobbyInfo(userVO);
        model.addAttribute("hobbyList", hobbyList);
        model.addAttribute("user", userVO);
        model.addAttribute("principal", principal);
        return "/user/read";
    }


    @ResponseBody
    @PostMapping("/updateUser")
    public Map<String, Object> updateUser(@RequestBody UserVO userVO) {
        Map<String, Object> result = new HashMap<>();
        StringBuilder sb = new StringBuilder();
                for (String role : userVO.getRoles()) {
                    sb.append(role).append(",");
                }
        // 마지막 쉼표 제거
        sb.deleteCharAt(sb.length() - 1);
        userVO.setRole(sb.toString());
        System.err.println(userVO);
        int updated = adminService.updateUserRoleAndLockedInfo(userVO);

        if (updated != 0) {
            result.put("status", 0);
        } else {
            result.put("status", -99);
            result.put("statusMessage", "실패");
        }
        return result;
    }
}
