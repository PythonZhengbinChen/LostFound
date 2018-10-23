package com.lostfound.dao;

import com.lostfound.bean.ThingsBean;

public interface FindDao {
	/**
	 * 实现发布失物功能接口
	 * @param things 角色信息实体类
	 * @return bool 判断物品是否添加成功
	 * @throws Exception 
	 */
	public boolean addThings(ThingsBean things)throws Exception;
}
