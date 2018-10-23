package com.lostfound.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lostfound.bean.ThanksBean;
import com.lostfound.service.ThanksService;

@Controller
public class ThanksAction{
	Map<String, String> map = new HashMap<String, String>(); 
	@Resource 
	ThanksService thanksService;
	
	/**
	 * <p>Title: ThanksSubmit</p>
	* <p>Description: 感谢Ta模块提交接口</p>
	* <p>Company: </p> 
	* <p>return type: String</p>
	* <p>Parameter: </p>
	 */
	@RequestMapping(value = "/thanks.do",method = RequestMethod.POST)
	@ResponseBody
	public String ThanksSubmit(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("name") String name,@RequestParam("email") String email,
			@RequestParam("department") String profession,@RequestParam("content") String content,
			@RequestParam("verify") String verify) throws Exception {
		request.setCharacterEncoding("utf-8");
		if(!checkValidationCode(request,verify))
			return null;
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//设置时间显示格式
		String id = sdf.format(date);//将当前时间格式化为需要的类型
		ThanksBean thanks=new ThanksBean();
		thanks.setId(id);
		thanks.setName(name);
		thanks.setEmail(email);
		thanks.setProfession(profession);
		thanks.setContent(content);
		thanksService.addThanks(thanks);
		map.put("success", "success");  
		JSONObject json=new JSONObject(map);
		return json.toString();
	}
	
	/**
	 * <p>Title: getThanksList</p>
	* <p>Description: 获取</p>
	* <p>Company: </p> 
	* <p>return type: List<ThanksBean></p>
	* <p>Parameter: </p>
	 */
	@RequestMapping(value = "/getThanksList.do",method = RequestMethod.POST)
	@ResponseBody
	public List<ThanksBean> getThanksList(HttpServletRequest request, HttpServletResponse response) {
		return thanksService.getThankList();
	}
	
	protected boolean checkValidationCode(HttpServletRequest request,String ValidationCode){
		String ValidationCodeSession=(String)request.getSession().getAttribute("verify");	//从HttpSession获取系统随机产生的验证码
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
