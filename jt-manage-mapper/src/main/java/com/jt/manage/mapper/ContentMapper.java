package com.jt.manage.mapper;

import java.util.List;

import com.jt.manage.mapper.base.mapper.SysMapper;
import com.jt.manage.pojo.Content;

public interface ContentMapper extends SysMapper<Content>{

    List<Content> queryListOrderByUpdated(Long categoryId);

}
