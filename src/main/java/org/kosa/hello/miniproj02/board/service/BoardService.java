package org.kosa.hello.miniproj02.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kosa.hello.miniproj02.board.mapper.BoardMapper;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardVO> getBoardList() { return boardMapper.getBoardList();
    }
}
