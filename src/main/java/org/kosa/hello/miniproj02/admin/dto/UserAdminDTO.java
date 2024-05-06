package org.kosa.hello.miniproj02.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAdminDTO {
    private List<String> usersId;
}
