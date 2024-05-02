package org.kosa.hello.miniproj02.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.kosa.hello.miniproj02.board.mapper.BoardMapper;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.kosa.hello.miniproj02.entity.FileVO;
import org.kosa.hello.miniproj02.file.mapper.FileMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;
    private final String CURR_IMAGE_REPO_PATH = "c:\\upload";
    private final SimpleDateFormat date_format = new SimpleDateFormat(File.separator + "YYYY" + File.separator + "MM" + File.separator + "dd");

    public List<BoardVO> getBoardList() { return boardMapper.getBoardList();
    }

    public int insert(BoardVO boardVO) {
       int boardInserted = boardMapper.boardInsert(boardVO);
       BoardVO insertedBoardVO = boardMapper.boardRead(boardVO);

       for(MultipartFile file : boardVO.getFile()){
           FileVO fileVO = saveFileInLocal(file,insertedBoardVO.getBoard_id());
           if(fileVO != null){
                fileVO.setBoard_id(insertedBoardVO.getBoard_id());
                fileMapper.saveFileInDB(fileVO);
           }
       }

        Document findImg = Jsoup.parse(boardVO.getContent());
        Element imgTag = findImg.select("img").first();

        if(imgTag != null) {
            String imgSrc = imgTag.attr("src");
            File file = new File(imgSrc);
            String fileId = file.getName();
            FileVO fileVO = fileMapper.getFile(fileId);

            fileVO.setBoard_id(insertedBoardVO.getBoard_id());
            fileVO.setFile_type("ck");
            fileMapper.saveCkFileInDB(fileVO);
        }

       return boardInserted;
    }

    private FileVO saveFileInLocal(MultipartFile file, @Nullable int board_id) {
        if (file == null || file.getName() == null) return null;
        Calendar now = Calendar.getInstance();
        String realFolder = date_format.format(now.getTime());
        File realPath = new File(CURR_IMAGE_REPO_PATH + realFolder);
        String file_save_name = UUID.randomUUID().toString();
        File realFile = new File(realPath, file_save_name);
        if(!realPath.exists()) {
            realPath.mkdirs();
        }
        try {
            file.transferTo(realFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        FileVO fileVO = new FileVO();
        fileVO.setFile_source(realFile.getAbsolutePath());
        fileVO.setFile_save_name(file_save_name);
        fileVO.setFile_origin_name(file.getOriginalFilename());
        fileVO.setBoard_id(board_id);
        return fileVO;
}

    public BoardVO boardRead(BoardVO boardVO) {
        return boardMapper.boardRead(boardVO);
    }

    public BoardVO boardDetailRead(BoardVO boardVO) {
            return boardMapper.boardDetailRead(boardVO);
        }

    public String saveCkFileInLocal(MultipartFile file) {
        if (file == null || file.getName() == null) return null;
        Calendar now = Calendar.getInstance();
        String realFolder = date_format.format(now.getTime());
        File realPath = new File(CURR_IMAGE_REPO_PATH + realFolder);
        String file_save_name = UUID.randomUUID().toString();
        File realFile = new File(realPath, file_save_name);
        if(!realPath.exists()) {
            realPath.mkdirs();
        }
        try {
            file.transferTo(realFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        FileVO fileVO = new FileVO();
        fileVO.setFile_source(realFile.getAbsolutePath());
        fileVO.setFile_save_name(file_save_name);
        fileVO.setFile_origin_name(file.getOriginalFilename());
        fileVO.setFile_type("ck");
        fileMapper.saveCkFileInDB(fileVO);
        return Integer.toString(fileVO.getFile_id());
}

    public FileVO getFile(String fileId) {
        return fileMapper.getFile(fileId);
    }

    public void uploadAndDownload(FileVO fileVO, HttpServletResponse response) throws IOException {
        OutputStream out = response.getOutputStream();
        if (fileVO == null) {
      			response.setStatus(404);
      		} else {

      			String originName = fileVO.getFile_origin_name();
      			originName = URLEncoder.encode(originName, "UTF-8");
      			//다운로드 할 때 헤더 설정
      			response.setHeader("Cache-Control", "no-cache");
      			response.addHeader("Content-disposition", "attachment; fileName="+originName);

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
