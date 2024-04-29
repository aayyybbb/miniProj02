package org.kosa.hello.miniproj02.hobby.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.hello.miniproj02.entity.HobbyVO;
import org.kosa.hello.miniproj02.entity.UserVO;

import java.util.List;

@Mapper
public interface HobbyMapper {
    List<HobbyVO> hobbyVOList();

    int hobbyInsert(@Param("user_id")String userId, @Param("hobby") List<String> hobby);

    List<HobbyVO> hobbyInfo(UserVO userVO);

    int hobbyDelete(@Param("user_id") String userId);
}
