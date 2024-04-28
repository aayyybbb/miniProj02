package org.kosa.hello.miniproj02.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.UserVO;

@Mapper
public interface UserMapper {
    int userInsert(UserVO userVO);
}
