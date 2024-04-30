package org.kosa.hello.miniproj02.board.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.board.service.BoardService;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        List<BoardVO> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        model.addAttribute("loginUser", principal.getName());
        return "board/list";
    }

    @GetMapping("/insertForm")
    public String insertForm(Model model, Principal principal) {
        model.addAttribute("loginUser", principal.getName());
        return "board/insertForm";
    }

//    @PostMapping("/insert")
//    public Map<String, Object> insertForm(MultipartFile boardVO) {
//        Map<String, Object> map = new HashMap<>();
//        if (boardUploded != 0 && hobbyUpdated != 0) {
//            map.put("status", 0);
//        } else {
//            map.put("status", -99);
//            map.put("statusMessage", "실패");
//        }
//
//        return map;
//    }
}
