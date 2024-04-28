package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
    private String userId;
    private String pwd;
    private String name;
    private String birth;
    private String gender;
    private String phone;
    private String addr;
    private String role;
    private int loginCount;

    private LocalDateTime locked_at;
    private LocalDateTime created_at;
    private LocalDateTime last_login;

    public UserVO(String userId) {
        this.userId = userId;
    }

}
