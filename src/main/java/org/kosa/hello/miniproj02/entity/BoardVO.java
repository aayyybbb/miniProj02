package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private int board_id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private int viewCount;
    private String pwd;
    private String writer;
    private String file_origin_name;
    private List<MultipartFile> file;
    private int file_id;
    private String user_id;
}
