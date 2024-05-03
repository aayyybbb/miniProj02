package org.kosa.hello.miniproj02.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.kosa.hello.miniproj02.entity.FileVO;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> getBoardList();

    int boardInsert(BoardVO boardVO);

    BoardVO boardRead(BoardVO boardVO);

    BoardVO boardDetailRead(BoardVO boardVO);

    int boardUpdate(BoardVO boardVO);
}

