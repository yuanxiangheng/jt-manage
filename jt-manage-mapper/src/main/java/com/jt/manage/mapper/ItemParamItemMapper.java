package com.jt.manage.mapper;

import com.jt.manage.mapper.base.mapper.SysMapper;
import com.jt.manage.pojo.ItemParamItem;

public interface ItemParamItemMapper extends SysMapper<ItemParamItem>{

    /**
     * 更加商品id更新规格参数数据
     * @param itemParamItem
     */
    void updateParamByItemId(ItemParamItem itemParamItem);

}
