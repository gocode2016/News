package cn.news.util;

import java.util.Comparator;

import cn.news.domain.News;

public class ComparatorNewsTime implements Comparator<News>{

	@Override
	public int compare(News n1, News n2) {
		// TODO Auto-generated method stub
		return n1.getNewsPubTime().compareTo(n2.getNewsPubTime());
	}

}
