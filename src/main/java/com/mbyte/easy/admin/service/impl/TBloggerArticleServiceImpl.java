package com.mbyte.easy.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbyte.easy.admin.entity.TBlogger;
import com.mbyte.easy.admin.entity.TBloggerArticle;
import com.mbyte.easy.admin.mapper.TBloggerArticleMapper;
import com.mbyte.easy.admin.service.ITBloggerArticleService;
import org.springframework.stereotype.Service;

@Service
public class TBloggerArticleServiceImpl extends ServiceImpl<TBloggerArticleMapper, TBloggerArticle> implements ITBloggerArticleService {
}
