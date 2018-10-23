package com.lostfound.dao.impl;

import org.springframework.stereotype.Service;

import com.lostfound.bean.FeedBackBean;
import com.lostfound.dao.FeedBackDao;
import com.lostfound.util.BaseDao;

@Service
public class FeedBackDaoImpl extends BaseDao implements FeedBackDao{
	/**
	 * 实现意见反馈功能接口
	 */
    public boolean addFeedBack(FeedBackBean feedback)throws Exception{
    	String sql="insert into feedback (id,name,email,profession,content) values(?,?,?,?,?)";
		int m=0;
		m=this.jdbcTemplate.update(sql, new Object[]{feedback.getId(),feedback.getName(),feedback.getEmail(),feedback.getProfession(),feedback.getContent()});
		if(m>0)
			return true;
		return false;
    	
    }
}
