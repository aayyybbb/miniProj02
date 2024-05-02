package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    private int file_id;
    @Nullable
    private int board_id;
    private String file_save_name;
    private String file_origin_name;
    private String file_source;
    private String file_type;
    private MultipartFile upload;
}
