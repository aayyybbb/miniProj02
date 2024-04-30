package org.kosa.hello.miniproj02.login.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.hello.miniproj02.entity.LoginDetails;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.login.mapper.LoginMapper;
import org.kosa.hello.miniproj02.scheduler.Scheduler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final LoginMapper loginMapper;
    private final Scheduler scheduler;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        loginMapper.plusCount(userId);
        UserVO userVO = loginMapper.login(new UserVO(userId));
        LoginDetails resultVO = new LoginDetails(scheduler,userVO);
        if (userVO == null) {
            throw new UsernameNotFoundException(userId + " 사용자가 존재하지 않습니다");
        }else if(userVO.getLogin_count() == 3){
            loginMapper.updateLockedTime(userId);
        }
        return resultVO;
    }
}
