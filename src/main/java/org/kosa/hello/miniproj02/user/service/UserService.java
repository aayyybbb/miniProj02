package org.kosa.hello.miniproj02.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.user.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public int userInsert(UserVO userVO) {
        String encodedPwd = passwordEncoder.encode(userVO.getPwd());
        userVO.setPwd(encodedPwd);
        userVO.setRole("USER");
        return userMapper.userInsert(userVO);
    }
}