package com.lostfound.service;

import com.lostfound.bean.FeedBackBean;

public interface FeedBackService {
	/**
	 * 实现发布意见反馈功能接口
	 */
	public boolean addFeedBack(FeedBackBean feedback)throws Exception;
}
