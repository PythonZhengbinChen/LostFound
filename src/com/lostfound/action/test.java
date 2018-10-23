package com.lostfound.action;

import com.lostfound.util.BaseDao;

public class test extends BaseDao{

	/**<p>Title: main</p>
	 * <p>Description: </p>
	 * <p>Company: </p> 
	 * <p>return type: void</p>
	 * <p>Parameter: </p>
	 * @author ZhengbinChen
	 * @date 2017-7-4
	 */
	
	public void tesst() {
		String sql = "UPDATE things SET time = 22222";
		this.jdbcTemplate.update(sql);
	}
	public static void main(String[] args) {
		test tt = new test();
		tt.tesst();
		
	}

}
