package cn.news.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dom4j.Visitor;

import cn.news.base.DaoSupport;
import cn.news.domain.Admin;
import cn.news.domain.Advice;

public interface VisitorService extends DaoSupport<Visitor>{
	/**
	 *  得到当前时间的这一年的访问量
	 * @return访问量
	 */
	Long getYearVisitor();
	
	/**
	 * 得到今天的访问量、绝对访问人次、在线用户、平均访问量、综合访问量、跳出率、新增访客
	 * @return Map访问量、绝对访问人次、在线用户、平均访问量、综合访问量、跳出率、新增访客
	 */
	
	Map<String,Long> getTodayVisitor();
	/**
	 * 得到今天的时间段访问量（每小时）
	 * @return Map<小时，访问量>
	 */
	Map<Date,Long> getToday();
}
