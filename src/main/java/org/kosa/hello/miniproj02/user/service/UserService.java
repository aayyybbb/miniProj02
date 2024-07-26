package org.kosa.hello.miniproj02.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.kosa.hello.miniproj02.entity.PageRequestVO;
import org.kosa.hello.miniproj02.entity.PageResponseVO;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.user.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public int userInsert(UserVO userVO) {
        String encodedPwd = passwordEncoder.encode(userVO.getPwd());
        userVO.setPwd(encodedPwd);
        userVO.setRole("USER");
        return userMapper.userInsert(userVO);
    }

    @Transactional
    public UserVO getUserInfo(String userId) {
        return userMapper.userInfo(new UserVO(userId));
    }

    @Transactional
    public int userUpdate(UserVO userVO) {
        String newPwd = passwordEncoder.encode(userVO.getPwd());
        userVO.setPwd(newPwd);
        return userMapper.userUpdate(userVO);
    }

    public int userDelete(String userId) {
        return userMapper.userDelete(new UserVO(userId));
    }

    public PageResponseVO<UserVO> getUserList(PageRequestVO pageRequestVO) {

        List<UserVO> list = userMapper.getUserList(pageRequestVO);
        int total = userMapper.getTotalCount(pageRequestVO);

        return PageResponseVO.<UserVO>withAll().list(list).total(total)
                .size(pageRequestVO.getSize()).pageNo(pageRequestVO.getPageNo()).build();
    }
}
