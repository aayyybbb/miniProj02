package org.kosa.hello.miniproj02.board.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.board.service.BoardService;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.kosa.hello.miniproj02.entity.FileVO;
import org.kosa.hello.miniproj02.entity.LoginDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.Principal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final ServletContext application;


    @GetMapping("/list")
    public String list(Model model, Authentication authentication) {
        LoginDetails loginDetails = (LoginDetails) authentication.getPrincipal();
        List<BoardVO> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        model.addAttribute("loginUser", loginDetails.getUserVO().getUser_id());
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

        if(updated != 0){
            result.put("status",0);
        }else {
            result.put("status", -99);
            result.put("statusMessage", "실패");
        }
        return result;
    }

    @GetMapping("/read/{board_id}")
    public String read(Model model, @PathVariable int board_id) {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(board_id);
        model.addAttribute("board", boardService.boardRead(boardVO));
        return "board/read";
    }

    @PostMapping("/ckUpload")
    @ResponseBody
    public Map<String, Object> ckUpload(FileVO fileVO) {
        MultipartFile file = fileVO.getUpload();
        String uploadedId = boardService.saveCkFileInLocal(file);
        String uploadPath = application.getContextPath() + "/board/ck/" + uploadedId;

        Map<String, Object> result = new HashMap<>();
        result.put("uploaded", true);
        result.put("url",uploadPath);
        return result;
    }

    @GetMapping("/ck/{uploadedId}")
   	public void image(@PathVariable("uploadedId") String uploadedId, HttpServletResponse response) throws Exception{
        OutputStream out = response.getOutputStream();
        FileVO fileVO = boardService.getCkImageFile(uploadedId);

   		if (fileVO == null) {
   			response.setStatus(404);
   		} else {

   			String originName = fileVO.getFile_source();
   			originName = URLEncoder.encode(originName, "UTF-8");
   			//다운로드 할 때 헤더 설정
   			response.setHeader("Cache-Control", "no-cache");
   			response.addHeader("Content-disposition", "attachment; fileName="+originName);
//   			response.setContentLength((int)boardImageFileVO.getSize());
//   			response.setContentType(boardImageFileVO.getContent_type());

   			//파일을 바이너리로 바꿔서 담아 놓고 responseOutputStream에 담아서 보낸다.
   			FileInputStream input = new FileInputStream(fileVO.getFile_source());

   			//outputStream에 8k씩 전달
   	        byte[] buffer = new byte[1024*8];

   	        while(true) {
   	        	int count = input.read(buffer);
   	        	if(count<0)break;
   	        	out.write(buffer,0,count);
   	        }
   	        input.close();
   	        out.close();
   		}
   	}


}

