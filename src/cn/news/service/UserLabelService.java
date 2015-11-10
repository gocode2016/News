package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.UserLabel;

public interface UserLabelService extends DaoSupport<UserLabel>{

	List<UserLabel> selectedLabel(Integer userId);
	
}
