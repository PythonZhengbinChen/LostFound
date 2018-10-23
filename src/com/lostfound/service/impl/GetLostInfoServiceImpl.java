package com.lostfound.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lostfound.bean.ThingsBean;
import com.lostfound.dao.GetLostInfoDao;
import com.lostfound.service.GetLostInfoService;

@Service
public class GetLostInfoServiceImpl implements GetLostInfoService{

	@Resource
	private GetLostInfoDao getLostFound;
	
	public List<ThingsBean> getAllThingsBeanList() {
		return getLostFound.getAllThingsBeanList();
	}

	public List<ThingsBean> getThingsBeanListByTypeCondition(
			String typeCondition) {
		return getLostFound.getThingsBeanListByTypeCondition(typeCondition);
	}

	public List<ThingsBean> getThingsBeanListByTimeCondition(
			String startCondition, String endCondition) {
		return getLostFound.getThingsBeanListByTimeCondition(startCondition, endCondition);
	}

	public List<ThingsBean> getThingsBeanListByCampusCondition(
			String campusCondition) {
		return getLostFound.getThingsBeanListByCampusCondition(campusCondition);
	}

	public ThingsBean getThingsBeanByThingsBeanId(String ThingsBeanId) {
		return getLostFound.getThingsBeanByThingsBeanId(ThingsBeanId);
	}

}
