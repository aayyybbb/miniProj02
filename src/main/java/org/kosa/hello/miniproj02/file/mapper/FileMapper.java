package org.kosa.hello.miniproj02.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.FileVO;

import java.util.List;

@Mapper
public interface FileMapper {
    void saveFileInDB(FileVO file);

    void saveCkFileInDB(FileVO file);

    FileVO getFile(String file_id);

    List<FileVO> deleteFileSource(int board_id);

    void deleteFileInDB(FileVO fileVO);
}
