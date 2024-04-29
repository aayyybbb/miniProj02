package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
    private String user_id;
    private String pwd;
    private String name;
    private String birth;
    private String gender;
    private String phone;
    private String addr;
    private String role;
    private int login_count;

    private List<String> hobby;
    private String searchKey;

    private LocalDateTime locked_at;
    private LocalDateTime created_at;
    private LocalDateTime last_login;

    public UserVO(String userId) {
        this.user_id = userId;
    }

}
