package org.kosa.hello.miniproj02.page.service;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.PageVO;
import org.kosa.hello.miniproj02.page.mapper.PageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageMapper pageMapper;

       public List<PageVO> getList() {
       	return pageMapper.getList();
   	}
}
