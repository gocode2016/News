package cn.news.util;

import java.util.Comparator;

import cn.news.domain.News;

public class ComparatorNewsComment implements Comparator<News>{

	@Override
	public int compare(News n1, News n2) {
		// TODO Auto-generated method stub
		return n1.getNewsCommentNum().compareTo(n2.getNewsCommentNum());
	}

}
