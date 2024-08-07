package org.kosa.hello.miniproj02.board.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.board.service.BoardService;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.kosa.hello.miniproj02.entity.FileVO;
import org.kosa.hello.miniproj02.entity.PageRequestVO;
import org.kosa.hello.miniproj02.page.service.PageService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final ServletContext application;
    private final PageService pageService;


    @GetMapping("/list")
    public String list(@Valid PageRequestVO pageRequestVO, BindingResult bindingResult, Model model, @Nullable Principal principal) {
        if (bindingResult.hasErrors()) {
            pageRequestVO = PageRequestVO.builder().build();
        }
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("pageResponseVO", boardService.getBoardList(pageRequestVO));
        if (principal != null) {
            model.addAttribute("loginUser", principal.getName());
        }
        model.addAttribute("sizes", pageService.getList());
        model.addAttribute("now", now);
        return "board/list";
    }

    @GetMapping("/insertForm")
    public String insertForm(Model model, Principal principal) {
        model.addAttribute("loginUser", principal.getName());
        return "board/insertForm";
    }

    @PostMapping("/board/insert")
    @ResponseBody
    public Map<String, Object> insert(BoardVO boardVO) {
        Map<String, Object> result = new HashMap<>();
        int updated = boardService.insert(boardVO);

        if (updated != 0) {
            result.put("status", 0);
        } else {
            result.put("status", -99);
            result.put("statusMessage", "실패");
        }
        return result;
    }

    @GetMapping("/read/{board_id}")
    public String read(Model model, @PathVariable int board_id, Principal principal) {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(board_id);
        BoardVO boardDetail = boardService.boardDetailRead(boardVO);
        if (boardDetail == null) {
            boardService.viewCountUp(board_id, principal);
            BoardVO boardRead = boardService.boardRead(boardVO);
            model.addAttribute("board", boardRead);
        } else {
            boardService.viewCountUp(board_id, principal);
            BoardVO boardDetails = boardService.boardDetailRead(boardVO);
            model.addAttribute("board", boardDetails);
        }
        return "board/read";
    }

    @ResponseBody
    @PostMapping("/update")
    public Map<String, Object> update(BoardVO boardVO) {
        System.err.println(boardVO.toString());
        Map<String, Object> result = new HashMap<>();
        if (boardVO.getPwd() != null && !boardVO.getPwd().isEmpty()) {
            if (!Objects.equals(boardVO.getPwd(), boardService.confirmPwd(boardVO))) {
                result.put("status", -90);
                result.put("statusMessage", "비밀번호가 틀립니다.");
                return result;
            }
        }

        int boardUpdated = boardService.update(boardVO);
        if (boardUpdated != 0) {
            result.put("status", 0);
        } else {
            result.put("status", -99);
            result.put("statusMessage", "실패");
        }
        return result;
    }

    @PostMapping("/ckUpload")
    @ResponseBody
    public Map<String, Object> ckUpload(FileVO fileVO) {
        MultipartFile file = fileVO.getUpload();
        String file_id = boardService.saveCkFileInLocal(file);
        String uploadPath = application.getContextPath() + "/board/uploadAndDownload/" + file_id;

        Map<String, Object> result = new HashMap<>();
        result.put("uploaded", true);
        result.put("url", uploadPath);
        return result;
    }

    @GetMapping("/uploadAndDownload/{file_id}")
    public void downLoadFile(@PathVariable("file_id") String fileId, HttpServletResponse response) throws Exception {
        FileVO fileVO = boardService.getFile(fileId);
        boardService.uploadAndDownload(fileVO, response);
    }

    @GetMapping("/updateForm/{board_id}")
    public String updateForm(Model model, Principal principal, @PathVariable int board_id) {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(board_id);
        BoardVO boardDetail = boardService.boardDetailRead(boardVO);
        if (boardDetail == null) {
            boardService.viewCountUp(board_id, principal);
            BoardVO boardRead = boardService.boardRead(boardVO);
            model.addAttribute("board", boardRead);
        } else {
            boardService.viewCountUp(board_id, principal);
            BoardVO boardDetails = boardService.boardDetailRead(boardVO);
            model.addAttribute("board", boardDetails);
        }
        return "board/updateForm";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody BoardVO boardVO) {
        System.err.println(boardVO.toString());
        Map<String, Object> result = new HashMap<>();
        if (boardVO.getPwd() != null && !boardVO.getPwd().isEmpty()) {
            if (!Objects.equals(boardVO.getPwd(), boardService.confirmPwd(boardVO))) {
                result.put("status", -90);
                result.put("statusMessage", "비밀번호가 틀립니다.");
                return result;
            }
        }
        int boardDeleted = boardService.delete(boardVO);
        if (boardDeleted != 0) {
            result.put("status", 0);
        } else {
            result.put("status", -99);
            result.put("statusMessage", "실패");
        }
        return result;
    }


}

