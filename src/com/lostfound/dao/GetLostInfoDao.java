package com.lostfound.dao;

import java.util.List;

import com.lostfound.bean.ThingsBean;


public interface GetLostInfoDao {
	public List<ThingsBean> getAllThingsBeanList();
	public List<ThingsBean> getThingsBeanListByTypeCondition(String typeCondition);
	public List<ThingsBean> getThingsBeanListByTimeCondition(String startCondition,String endCondition);
	public List<ThingsBean> getThingsBeanListByCampusCondition(String campusCondition);
	public ThingsBean getThingsBeanByThingsBeanId(String ThingsBeanId);
	
}
