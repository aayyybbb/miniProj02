package org.kosa.hello.miniproj02.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.BoardVO;
import org.kosa.hello.miniproj02.entity.PageRequestVO;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> getBoardList(PageRequestVO pageRequestVO);

    int boardInsert(BoardVO boardVO);

    BoardVO boardRead(BoardVO boardVO);

    BoardVO boardDetailRead(BoardVO boardVO);

    int boardUpdate(BoardVO boardVO);

    int deleteBoard(BoardVO boardVO);

    int getTotalCount(PageRequestVO pageRequestVO);

    String confirmPwd(BoardVO boardVO);

    void viewCountUp(BoardVO boardVO);
}

