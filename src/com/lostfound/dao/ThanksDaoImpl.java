package com.lostfound.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.lostfound.bean.ThanksBean;
import com.lostfound.util.BaseDao;

@Service
public class ThanksDaoImpl  extends BaseDao implements ThanksDao{
	/**
	 * 实现发布感谢信功能接口
	 * @param things 角色信息实体类
	 * @return bool 判断物品是否更改成功
	 * @throws Exception 
	 */
	public boolean addThanks(ThanksBean thanks)throws Exception{
		String sql="insert into thanks (id,name,email,profession,content) values(?,?,?,?,?)";
		int m=0;
		m=this.jdbcTemplate.update(sql, new Object[]{thanks.getId(),thanks.getName(),thanks.getEmail(),thanks.getProfession(),thanks.getContent()});
		if(m>0)
			return true;
		return false;
	}
	/**
	 * * <p>Title: getThankList</p>
	* <p>Description: 获取感谢列表</p>
	* <p>Company: YSU</p> 
	* <p>Parameter: </p>
	 */
	public List<ThanksBean> getThankList() {
		String sql = "select * from thanks";
		return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ThanksBean.class));
	}
}
