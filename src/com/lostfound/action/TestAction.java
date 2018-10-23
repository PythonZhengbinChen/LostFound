package com.lostfound.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestAction{

	/***
	 * <p>Title: test</p>
	* <p>Description: spring IOP格式模板，所有的Action都可以仿照这种格式写</p>
	* <p>Company: </p> 
	* <p>return type: String</p>
	* <p>Parameter: </p>
	* @author ZhengbinChen
	* @date 2017-7-4
	 */
	@RequestMapping(value = "/test.do",method = RequestMethod.GET)
	@ResponseBody
	public String test(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("============页面跳转==============");
		return "test";
	}
	
	@RequestMapping(value = "/test1.do",method = RequestMethod.GET)
	@ResponseBody
	public String test1(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("============页面跳转==============");
		return "test";
	}
	
	public static void main(String[] args) {
		
	}
}
