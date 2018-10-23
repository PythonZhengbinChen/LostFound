package com.lostfound.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lostfound.bean.FeedBackBean;
import com.lostfound.dao.FeedBackDao;
import com.lostfound.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{
	@Resource
	FeedBackDao feedbackdao;
	/**
	 * 实现发布意见反馈的功能
	 */

	public boolean addFeedBack(FeedBackBean feedback) throws Exception {
		return feedbackdao.addFeedBack(feedback);
	}

}
