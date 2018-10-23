package com.lostfound.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lostfound.bean.ThingsBean;
import com.lostfound.service.GetLostInfoService;

/**
 * <p>Title: GetLostInfoAction</p>
* <p>Description: 首页失物信息展示</p>
* <p>Company: YSU</p> 
* @author ZhengbinChen 
* @date 2017-7-5 上午9:49:11
**
 */
@Controller
public class GetLostInfoAction {
	@Resource
	private GetLostInfoService getLostInfoService;
	
	@RequestMapping(value="/getLostThingInfoList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<ThingsBean> getLostThingInfoList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求获取所有列表信息！");
		try {
			if(getLostInfoService.getAllThingsBeanList().size() != 0) {
				return getLostInfoService.getAllThingsBeanList();
			}
		} catch (NullPointerException e) {
			System.out.println("数据库中没有信息数据");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value="/getLostThingInfoListByCampusConditon.do", method = RequestMethod.POST)
	@ResponseBody
	public List<ThingsBean> getLostThingInfoListByCampusConditon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("campus") String campus) {
		if(campus.equals("2")) {
			return this.getLostThingInfoList(request, response);
		}
		try {
			if(getLostInfoService.getThingsBeanListByCampusCondition(campus).size() != 0) {
				return getLostInfoService.getThingsBeanListByCampusCondition(campus);
			}
		} catch (NullPointerException e) {
			System.out.println("数据库中没有信息数据");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value="/getLostThingInfoListByTypeConditon.do", method = RequestMethod.POST)
	@ResponseBody
	public List<ThingsBean> getLostThingInfoListByTypeConditon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String type) {
		if(type.equals("9")) {
			return this.getLostThingInfoList(request, response);
		}
		try {
			if(getLostInfoService.getThingsBeanListByTypeCondition(type).size() != 0) {
				return getLostInfoService.getThingsBeanListByTypeCondition(type);
			}
		} catch (NullPointerException e) {
			System.out.println("数据库中没有信息数据");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value="/getLostThingInfoListByTimeConditon.do", method = RequestMethod.POST)
	@ResponseBody
	public List<ThingsBean> getLostThingInfoListByTimeConditon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("time") String time) {
		String newTime = null;
		String endTime = null;
		if(time.equals("0")) {
			return this.getLostThingInfoList(request, response);
		} else if(time.equals("1")) {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			newTime = df.format(date);
			endTime = newTime;
		} else if(time.equals("2")) {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			newTime = df.format(date);
			endTime = df.format(new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000));
		}else {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			newTime = df.format(date);
			Long ord = (long) (20 * 24 * 60 * 60 * 1000);
			endTime = df.format(new Date(date.getTime() - ord));
		}
		try {
			if(getLostInfoService.getThingsBeanListByTimeCondition(newTime, endTime).size() != 0) {
				return getLostInfoService.getThingsBeanListByTimeCondition(newTime, endTime);
			}
		} catch (NullPointerException e) {
			System.out.println("数据库中没有信息数据");
			return null;
		}
		return null;
	}
	
	@RequestMapping(value="/gotoGetLostThingById.do", method = RequestMethod.GET)
	@ResponseBody
	public void gotoGetLostThingById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id) {
		System.out.println(id);
		
			request.getSession().setAttribute("thingBeanId",id);
			ServletContext application= request.getSession().getServletContext();
			int visitNum = 0;
			try {
				visitNum = (Integer)application.getAttribute(id);
			} catch (NullPointerException e) {
				System.out.println("创建新的id");
			}
			visitNum += 1;
			application.setAttribute(id, visitNum);
		
		try {
			response.sendRedirect("/LostFound/goods.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getLostThingById.do", method = RequestMethod.POST)
	@ResponseBody
	public ThingsBean getLostThingById(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = (String)request.getSession().getAttribute("thingBeanId");
			return getLostInfoService.getThingsBeanByThingsBeanId(id);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/getLostThingWebVisitCountById.do", method = RequestMethod.POST)
	@ResponseBody
	public int getLostThingWebVisitCountById(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = (String)request.getSession().getAttribute("thingBeanId");
			int visitNum = (Integer)request.getSession().getServletContext().getAttribute(id);
			return visitNum;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return 1;
		}
	}

	@RequestMapping(value="/getFounderInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public ThingsBean getFounderInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") String id,@RequestParam("verify") String verify) {
		if(verify.equalsIgnoreCase((String)request.getSession().getAttribute("verify")))
			return getLostInfoService.getThingsBeanByThingsBeanId(id);
		return null;
	}
	
	

}
