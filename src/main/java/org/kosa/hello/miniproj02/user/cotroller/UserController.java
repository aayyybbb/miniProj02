package org.kosa.hello.miniproj02.user.cotroller;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/insertForm")
    public String insertForm() {
        return "/user/insertForm";
    }

    @PostMapping("/insert")
    @ResponseBody
    public Map<String, Object> insert(@RequestBody UserVO userVO) {
        int updated = userService.userInsert(userVO);
        Map<String, Object> map = new HashMap<>();
        if (updated != 0) {
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "실패");
        }

        return map;
    }
}
