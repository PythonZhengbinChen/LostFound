package com.lostfound.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lostfound.bean.ThingsBean;
import com.lostfound.dao.FindDao;

@Service
public class FindServiceImpl implements FindService{
	@Resource 
	FindDao findDao;
	
	/**
	 * 实现发布失物功能接口
	 * @param things 角色信息实体类
	 * @return bool 判断物品是否添加成功
	 * @throws Exception 
	 */
	public boolean addThings(ThingsBean things)throws Exception{
		return findDao.addThings(things);
	}
}
