package com.lu.usermanage.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lu.usermanage.common.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lu.usermanage.entity.WordsEntity;
import com.lu.usermanage.service.WordsService;




/**
 * 
 *
 * @author lu
 * @email 1157076680@qq.com
 * @date 2020-06-12 15:51:11
 */
@RestController
@RequestMapping("usermanage/words")
@CrossOrigin( origins="http://localhost:8080")
public class WordsController {
    @Autowired
    private WordsService wordsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("usermanage:words:list")
    public R list(){

        List<WordsEntity> list = wordsService.list();

        return R.ok().put("page", list);
    }
    /**
     * 分页查询，一页1条数据(单次闯关)
     */
    @GetMapping("/listPages")
    public  R listPages(Integer currentPage,Integer pageSize){
        Page<WordsEntity> pageUtils=new Page<>(currentPage,pageSize);
        Page<WordsEntity> page = wordsService.page(pageUtils, null);
        return R.ok().put("page",page);
    }






    /**
     * 信息
     */
    @RequestMapping("/info")
    //@RequiresPermissions("usermanage:words:info")
    public R info( Integer id){
		WordsEntity words = wordsService.getById(id);

        return R.ok().put("words", words);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("usermanage:words:save")
    public R save(WordsEntity words){
		wordsService.save(words);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("usermanage:words:update")
    public R update(WordsEntity words){
        boolean update=wordsService.updateById(words);
        if(update){
            return R.ok("修改成功");
        }else{
            return R.error(555, "修改失败");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("usermanage:words:delete")
    public R delete(Integer[] ids){
		wordsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
