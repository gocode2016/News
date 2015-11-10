package cn.news.service;

import java.util.List;

import cn.news.base.DaoSupport;
import cn.news.domain.PicCollection;

public interface PicCollectionService extends DaoSupport<PicCollection>{
	PicCollection getCover(Integer newsId);

	List<PicCollection> showPic(Integer newsId);
}
