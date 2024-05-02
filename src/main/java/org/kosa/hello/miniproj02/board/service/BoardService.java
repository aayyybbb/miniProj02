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

import java.io.File;
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
            FileVO fileVO = fileMapper.getCkImageFile(fileId);

            fileVO.setBoard_id(insertedBoardVO.getBoard_id());
            fileMapper.saveFileInDB(fileVO);
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
        fileMapper.saveCkFileInDB(fileVO);
        return Integer.toString(fileVO.getFile_id());
}

    public FileVO getCkImageFile(String fileId) {
        return fileMapper.getCkImageFile(fileId);
    }
}
