package com.lostfound.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.lostfound.bean.ThingsBean;
import com.lostfound.dao.GetLostInfoDao;
import com.lostfound.util.BaseDao;

@Service
public class GetLostInfoDaoImpl extends BaseDao implements GetLostInfoDao {

	public List<ThingsBean> getAllThingsBeanList() {
		String sql = "select * from things";
		/**使用jdbcTemplate时，查询时要使用下面的还是进行实体和行的转换，将一行作为一个实体**/
		return this.jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ThingsBean.class));
	}

	public List<ThingsBean> getThingsBeanListByTypeCondition(
			String typeCondition) {
		String sql = "select * from things where type = ? ";
		return this.jdbcTemplate.query(sql, 
				new Object[] {Integer.valueOf(typeCondition)}, ParameterizedBeanPropertyRowMapper.newInstance(ThingsBean.class));
	}

	public List<ThingsBean> getThingsBeanListByTimeCondition(
			String startCondition,String endCondition) {
		String sql = "select * from things where time <= ? and time >= ?";
		return this.jdbcTemplate.query(sql,
				new Object[] {startCondition,endCondition}, ParameterizedBeanPropertyRowMapper.newInstance(ThingsBean.class));
	}

	public List<ThingsBean> getThingsBeanListByCampusCondition(
			String campusCondition) {
		String sql = "select * from things where campus = ? ";
		return this.jdbcTemplate.query(sql, 
				new Object[] {Integer.valueOf(campusCondition)}, ParameterizedBeanPropertyRowMapper.newInstance(ThingsBean.class));
	}

	public ThingsBean getThingsBeanByThingsBeanId(String ThingsBeanId) {
		String sql = "select * from things where id = ?";
		return this.jdbcTemplate.queryForObject(sql,
				new Object[] {ThingsBeanId},ParameterizedBeanPropertyRowMapper.newInstance(ThingsBean.class));
	}

}
