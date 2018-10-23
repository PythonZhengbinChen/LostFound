package com.lostfound.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lostfound.bean.FeedBackBean;
import com.lostfound.service.FeedBackService;

@Controller
public class FeedBackAction{
	Map<String, String> map = new HashMap<String, String>();
	@Resource
	FeedBackService feedbackService;
	
	
	@RequestMapping(value = "/feedback.do",method = RequestMethod.POST)
	@ResponseBody
	public String FeedBackSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String profession=request.getParameter("department");
		String content=request.getParameter("content");
		String verify=request.getParameter("verify");
		if(!checkValidationCode(request,verify))
			return null;
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String id = sdf.format(date);
		FeedBackBean feedback = new FeedBackBean();
		feedback.setId(id);
		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setProfession(profession);
		feedback.setContent(content);
		feedbackService.addFeedBack(feedback);
		map.put("success", "success");  
		JSONObject json=new JSONObject(map);
		return json.toString();
	}
	
	
	protected boolean checkValidationCode(HttpServletRequest request,String ValidationCode){
		String ValidationCodeSession=(String)request.getSession().getAttribute("verify");	//从HttpSession获取系统随机产生的验证码
		//若ValidationCodeSession为null,说明验证码过期
		if(ValidationCodeSession==null){
			System.out.println("验证码过期");
			request.setAttribute("message", "验证码过期");	//设置需要的结果信息
			map.put("message", "验证码过期");
			return false;
		}
		//将用户输入的验证码与系统随机生成的验证码相比较，判断是否正确
		if(!ValidationCode.equalsIgnoreCase(ValidationCodeSession)){
			System.out.println("验证码不正确");
			//request.setAttribute("message", "验证码不正确");	//设置需要的结果信息
			map.put("message", "验证码不正确");
			return false;
		}	
		return true;
	}
}
