package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.ContentCategory;
import com.jt.manage.service.ContentCategoryService;

@RequestMapping("content/category")
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("list")
    @ResponseBody
    public List<ContentCategory> queryList(@RequestParam(value = "id", defaultValue = "0") Long id) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(id);
        return this.contentCategoryService.queryListByWhere(contentCategory);
    }

    @RequestMapping("create")
    @ResponseBody
    public SysResult createNode(@RequestParam("parentId") Long parentId, @RequestParam("name") String name) {

        ContentCategory contentCategory = this.contentCategoryService.createNode(parentId, name);

        return SysResult.ok(contentCategory);// 返回

    }

    /**
     * 重命名
     * 
     * @param contentCategory
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public SysResult updateNode(ContentCategory contentCategory) {
        this.contentCategoryService.updateNode(contentCategory);
        return SysResult.ok();
    }

    @RequestMapping("delete")
    @ResponseBody
    public SysResult deleteNode(@RequestParam("parentId") Long parentId, @RequestParam("id") Long id) {
        this.contentCategoryService.deleteNode(parentId, id);
        return SysResult.ok();
    }

}
