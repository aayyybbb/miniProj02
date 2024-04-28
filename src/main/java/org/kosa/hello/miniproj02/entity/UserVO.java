package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String locked_at;
    private String created_at;

    public UserVO(String userId) {
        this.userId = userId;
    }

}
