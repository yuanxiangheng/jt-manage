package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ContentMapper;
import com.jt.manage.pojo.Content;

@Service
public class ContentService extends BaseService<Content> {

    @Autowired
    private ContentMapper contentMapper;

    public EasyUIResult queryList(Long categoryId, Integer page, Integer rows) {
        // 设置分页
        PageHelper.startPage(page, rows, true);
        List<Content> contents = this.contentMapper.queryListOrderByUpdated(categoryId);
        PageInfo<Content> pageInfo = new PageInfo<Content>(contents);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

}
