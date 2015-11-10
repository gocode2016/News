package cn.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.NewsLabel;
import cn.news.domain.NewsType;
import cn.news.domain.UserLabel;
import cn.news.service.NewsLabelService;
import cn.news.service.NewsTypeService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class NewsTypeServiceImpl extends DaoSupportImpl<NewsType> implements NewsTypeService{


}
