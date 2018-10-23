package com.lostfound.dao;

import com.lostfound.bean.FeedBackBean;

public interface FeedBackDao {
	/**
	 * 实现意见反馈功能接口
	 */
	public boolean addFeedBack(FeedBackBean feedback)throws Exception;

}
