package com.jt.manage.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemDescService;
import com.jt.manage.service.ItemService;

/**
 * 商品相关的业务逻辑处理
 * 
 */
@RequestMapping("item")
@Controller
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;

    /**
     * 新增商品数据
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public SysResult saveItem(Item item, @RequestParam("desc") String desc,
            @RequestParam("itemParams") String itemParams) {

        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        item.setStatus(1);
        if (item.getId() != null) {
            LOGGER.warn("传入的商品数据中包含id数据! id = {}", item.getId());
        }
        item.setId(null);// 安全方面考虑，强制设置id为null
        LOGGER.info("新增商品数据! title = {}, cid = {}", item.getTitle(), item.getCid());

        // 保存到数据库
        try {
            this.itemService.saveItem(item, desc, itemParams);
        } catch (Exception e) {
            LOGGER.error("保存失败! title = " + item.getTitle(), e);
            // 返回错误状态
            return SysResult.build(500, "新增商品数据失败!");
        }

        if (LOGGER.isDebugEnabled()) {// 判断debug是否启用
            LOGGER.debug("商品添加成功! item = " + item);
        }

        return SysResult.ok();
    }

    @RequestMapping("query")
    @ResponseBody
    public EasyUIResult queryList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) {
        return this.itemService.queryList(page, rows);
    }

    /**
     * 通过id查询商品数据
     * 
     * @param id
     * @return
     */
    @RequestMapping("query/{id}")
    @ResponseBody
    public SysResult queryList(@PathVariable("id") Long id) {
        try {
            Item item = this.itemService.queryById(id);
            if (item != null && item.getId() != null) {
                return SysResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(202, "查询失败! id = " + id);
        }
        return SysResult.build(201, "查询不到商品数据 id = " + id);
    }

   @RequestMapping(value = "query/item/desc/{itemId}", method = RequestMethod.GET)
   @ResponseBody
   public SysResult queryItemDescByItemId(@PathVariable("itemId") Long itemId) {
       ItemDesc itemDesc = this.itemDescService.queryById(itemId);
       return SysResult.ok(itemDesc);
   }

   @RequestMapping(value = "update", method = RequestMethod.POST)
   @ResponseBody
   public SysResult updateItem(Item item, @RequestParam("desc") String desc,
           @RequestParam("itemParams") String itemParams) {
       this.itemService.updateItem(item, desc, itemParams);
       return SysResult.ok();
   }

   @RequestMapping(value = "delete", method = RequestMethod.POST)
   @ResponseBody
   public SysResult deleteItem(@RequestParam("ids") String ids) {
       this.itemService.deleteByIds(ids.split(","));
       return SysResult.ok();
   }
}
