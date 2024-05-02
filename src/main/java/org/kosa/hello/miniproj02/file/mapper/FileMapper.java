package org.kosa.hello.miniproj02.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.FileVO;

@Mapper
public interface FileMapper {
    void saveFileInDB(FileVO file);

    void saveCkFileInDB(FileVO file);

    FileVO getCkImageFile(String file_id);
}
