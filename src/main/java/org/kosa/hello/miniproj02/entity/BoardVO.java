package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String user_id;
}
