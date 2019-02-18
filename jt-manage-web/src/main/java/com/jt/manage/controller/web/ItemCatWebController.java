package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

/**
 * 对外提供接口
 * 
 */
@RequestMapping("web/itemcat")
@Controller
public class ItemCatWebController {
    
    @Autowired
    private ItemCatService itemCatService;
    
    @RequestMapping("all")
    @ResponseBody
    public ItemCatResult queryAll(){
        return this.itemCatService.queryItemCatWebAll();
    }

}
