package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.Advice;

public interface AdviceService extends DaoSupport<Advice>{
	
	List<Advice> showAdvice(Integer advId);
}
