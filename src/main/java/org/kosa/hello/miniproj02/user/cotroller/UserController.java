package org.kosa.hello.miniproj02.user.cotroller;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.HobbyVO;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.hobby.service.HobbyService;
import org.kosa.hello.miniproj02.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
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
    @PostMapping("/insert")
    public Map<String, Object> insert(@RequestBody UserVO userVO) {
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

    @GetMapping("/myPage")
    public String myPage(Model model, Principal principal) {
        UserVO userVO = userService.getUserInfo(principal.getName());
        List<HobbyVO> hobbyList = hobbyService.getHobbyInfo(userVO);
        model.addAttribute("hobbyList", hobbyList);
        model.addAttribute("user", userVO);
        return "/user/read";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, Principal principal) {

        UserVO userVO = userService.getUserInfo(principal.getName());
        List<HobbyVO> hobbyList = hobbyService.getHobbyInfo(userVO);
        List<HobbyVO> hobbyVOList = hobbyService.getHobbyList();

        model.addAttribute("hobby", hobbyVOList);
        model.addAttribute("hobbyList", hobbyList);
        model.addAttribute("user", userVO);
        return "/user/updateForm";
    }

    @ResponseBody
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody UserVO userVO) {
        int userUpdated = userService.userUpdate(userVO);
        int hobbyUpdated = hobbyService.hobbyUpdate(userVO.getUser_id(),userVO.getHobby());
        Map<String, Object> map = new HashMap<>();
        if (userUpdated != 0 && hobbyUpdated != 0) {
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "실패");
        }

        return map;
    }

    @ResponseBody
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody UserVO userVO) {
        Map<String, Object> map = new HashMap<>();
       int userDeleted = userService.userDelete(userVO.getUser_id());
       if(userDeleted != 1){
           map.put("status", 1);
       }else{
           map.put("status", -99);
           map.put("statusMessage", "탈퇴 실패");
       }
       return map;
    }

}
