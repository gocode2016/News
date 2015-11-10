package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.Develop;

public interface DevelopService extends DaoSupport<Develop>{

	List<Develop> findById(Integer uid);

}
