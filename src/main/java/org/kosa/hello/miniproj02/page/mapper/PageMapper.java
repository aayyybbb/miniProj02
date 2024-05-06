package org.kosa.hello.miniproj02.page.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.hello.miniproj02.entity.PageVO;

import java.util.List;

@Mapper
public interface PageMapper {
    List<PageVO> getList();
}
