package com.lu.usermanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lu.usermanage.common.utils.PageUtils;
import com.lu.usermanage.entity.WordsEntity;

import java.util.Map;

/**
 * 
 *
 * @author lu
 * @email 1157076680@qq.com
 * @date 2020-06-12 15:51:11
 */
public interface WordsService extends IService<WordsEntity> {

    PageUtils queryPage(Map<String, Object> params);


}

