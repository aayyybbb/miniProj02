package org.kosa.hello.miniproj02.admin.service;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserMapper userMapper;

    public int updateUserRoleAndLockedInfo(UserVO userVO) {
        return userMapper.updateUserRoleAndLockedInfo(userVO);
    }
}
