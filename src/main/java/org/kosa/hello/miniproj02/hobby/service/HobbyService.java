package org.kosa.hello.miniproj02.hobby.service;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.HobbyVO;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.hobby.mapper.HobbyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {
    private final HobbyMapper hobbyMapper;

    @Transactional
    public List<HobbyVO> getHobbyList(){
        return hobbyMapper.hobbyVOList();
    }

    @Transactional
    public int hobbyInsert(String userId, List<String> hobby) {
        return hobbyMapper.hobbyInsert(userId,hobby);
    }

    @Transactional
    public List<HobbyVO> getHobbyInfo(UserVO userVO){
        return hobbyMapper.hobbyInfo(userVO);
    }

    @Transactional
    public int hobbyDelete(String userId){
        return hobbyMapper.hobbyDelete(userId);
    }

    @Transactional
    public int hobbyUpdate(String userId, List<String> hobby) {
        int deleted = hobbyDelete(userId);
        int inserted = hobbyMapper.hobbyInsert(userId,hobby);
        if(deleted != 0 && inserted != 0){
            return 1;
        }else{
            return 0;
        }
    }
}
