package com.lostfound.service;

import java.util.List;

import com.lostfound.bean.ThanksBean;

public interface ThanksService {
	/**
	 * 实现发布感谢信功能接口
	 * @param things 角色信息实体类
	 * @return bool 判断物品是否更改成功
	 * @throws Exception 
	 */
	public boolean addThanks(ThanksBean thanks)throws Exception;
	
	public List<ThanksBean> getThankList();
}
