package org.kosa.hello.miniproj02.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.UserVO;

@Mapper
public interface LoginMapper {
    UserVO login(UserVO userVO);
}
