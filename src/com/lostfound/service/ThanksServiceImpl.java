package com.lostfound.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lostfound.bean.ThanksBean;
import com.lostfound.dao.ThanksDao;

@Service
public class ThanksServiceImpl implements ThanksService{
	@Resource
	ThanksDao thanksdao;
	/**
	 * 实现发布感谢信功能接口
	 * @param things 角色信息实体类
	 * @return bool 判断物品是否更改成功
	 * @throws Exception 
	 */
	public boolean addThanks(ThanksBean thanks)throws Exception{
		return thanksdao.addThanks(thanks);
	}
	public List<ThanksBean> getThankList() {
		return thanksdao.getThankList();
	}
}
