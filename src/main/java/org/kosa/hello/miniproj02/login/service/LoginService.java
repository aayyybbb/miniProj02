package org.kosa.hello.miniproj02.login.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.hello.miniproj02.entity.LoginDetails;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.login.mapper.LoginMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final LoginMapper loginMapper;
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserVO resultVO = loginMapper.login(new UserVO(userId));
        if (resultVO == null) {
      			throw new UsernameNotFoundException(userId + " 사용자가 존재하지 않습니다");
      		}
        return new LoginDetails(loginMapper.login(new UserVO(userId)));
    }
}
