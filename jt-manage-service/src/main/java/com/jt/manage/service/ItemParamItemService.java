package com.jt.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manage.mapper.ItemParamItemMapper;
import com.jt.manage.pojo.ItemParamItem;

@Service
public class ItemParamItemService extends BaseService<ItemParamItem> {
    
    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    public void updateParamByItemId(ItemParamItem itemParamItem) {
        this.itemParamItemMapper.updateParamByItemId(itemParamItem);
    }

}
