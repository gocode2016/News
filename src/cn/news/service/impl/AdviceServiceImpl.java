package cn.news.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.news.base.DaoSupportImpl;
import cn.news.domain.Advice;
import cn.news.service.AdviceService;

@Service("adviceService")
@Transactional
@SuppressWarnings("unchecked")
public class AdviceServiceImpl extends DaoSupportImpl<Advice> implements AdviceService{

	/**
	 * 根据广告id获取广告
	 */
	@Override
	public List<Advice> showAdvice(Integer advId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Advice a where a.advId = "+advId+"").list();
	}

}
