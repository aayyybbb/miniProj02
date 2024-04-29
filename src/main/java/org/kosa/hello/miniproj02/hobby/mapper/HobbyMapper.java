package org.kosa.hello.miniproj02.hobby.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.hello.miniproj02.entity.HobbyVO;

import java.util.List;

@Mapper
public interface HobbyMapper {
    List<HobbyVO> hobbyVOList();

    int hobbyInsert(@Param("user_id")String userId, @Param("hobby") List<String> hobby);
}
