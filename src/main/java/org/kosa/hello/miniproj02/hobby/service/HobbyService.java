package org.kosa.hello.miniproj02.hobby.service;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.HobbyVO;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.hobby.mapper.HobbyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    public List<HobbyVO> getHobbyList(){
        return hobbyMapper.hobbyVOList();
    }

    public int hobbyInsert(String userId, List<String> hobby) {
        return hobbyMapper.hobbyInsert(userId,hobby);
    }

    public List<HobbyVO> getHobbyInfo(UserVO userVO){
        return hobbyMapper.hobbyInfo(userVO);
    }
}
