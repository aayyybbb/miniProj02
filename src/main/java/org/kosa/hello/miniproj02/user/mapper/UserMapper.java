package org.kosa.hello.miniproj02.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.PageRequestVO;
import org.kosa.hello.miniproj02.entity.UserVO;

import java.util.List;

@Mapper
public interface UserMapper {
    int userInsert(UserVO userVO);

    int userUpdate(UserVO userVO);

    List<UserVO> userVOList();

    UserVO userInfo(UserVO userVO);

    int updateUserRoleAndLockedInfo(UserVO userVO);

    int userDelete(UserVO userVO);
    
    List<UserVO> getUserList(PageRequestVO pageRequestVO);

    int getTotalCount(PageRequestVO pageRequestVO);
}
