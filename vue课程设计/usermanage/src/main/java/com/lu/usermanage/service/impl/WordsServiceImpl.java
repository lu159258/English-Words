package com.lu.usermanage.service.impl;

import com.lu.usermanage.common.utils.PageUtils;
import com.lu.usermanage.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lu.usermanage.dao.WordsDao;
import com.lu.usermanage.entity.WordsEntity;
import com.lu.usermanage.service.WordsService;


@Service("wordsService")
public class WordsServiceImpl extends ServiceImpl<WordsDao, WordsEntity> implements WordsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WordsEntity> page = this.page(
                new Query<WordsEntity>().getPage(params),
                new QueryWrapper<WordsEntity>()
        );

        return new PageUtils(page);
    }

}