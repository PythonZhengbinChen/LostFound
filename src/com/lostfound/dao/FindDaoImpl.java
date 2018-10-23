package com.lostfound.dao;

import org.springframework.stereotype.Service;

import com.lostfound.bean.ThingsBean;
import com.lostfound.util.BaseDao;

@Service
public class FindDaoImpl extends BaseDao implements FindDao{
	/**
	 * 实现发布失物功能接口
	 * @param things 角色信息实体类
	 * @return bool 判断物品是否更改成功
	 * @throws Exception 
	 */
	public boolean addThings(ThingsBean things)throws Exception{
		String sql="insert into things (id,feature,time,place,campus,type,description,name,email,phone) values(?,?,?,?,?,?,?,?,?,?)";
		int m=0;
		m=this.jdbcTemplate.update(sql, new Object[]{things.getId(),things.getFeature(),things.getTime(),things.getPlace(),things.getCampus(),things.getType(),things.getDescription(),things.getName(),things.getEmail(),things.getPhone()});
		if(m>0)
			return true;
		return false;
	}
}
