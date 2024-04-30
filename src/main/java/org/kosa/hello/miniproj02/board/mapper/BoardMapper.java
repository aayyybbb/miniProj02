package org.kosa.hello.miniproj02.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.BoardVO;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> getBoardList();
}

