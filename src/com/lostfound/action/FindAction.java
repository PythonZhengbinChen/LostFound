package com.lostfound.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lostfound.bean.ThingsBean;
import com.lostfound.service.FindService;

@SuppressWarnings("serial")
@Controller
public class FindAction extends HttpServlet {
	String filename;
	String filePath;
	Map<String, String> map = new HashMap<String,String>(); 
	private FileOutputStream out;
	@Resource 
	FindService findService;
	
	
	@RequestMapping(value = "/find.do",method = RequestMethod.POST)
	@ResponseBody
	public String findSubmit(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("goods") String feature,@RequestParam("date") String time,
			@RequestParam("address") String place,@RequestParam("campus") int campus,
			@RequestParam("category") int type,@RequestParam("description") String description,
			@RequestParam("name") String name,@RequestParam("email") String email,
			@RequestParam("tel") String phone,@RequestParam("verify") String verify,
			@RequestParam("file") MultipartFile file) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		if(!checkValidationCode(request,verify))
			return null;
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//设置时间显示格式
		String id = sdf.format(date);//将当前时间格式化为需要的类型
		ThingsBean things=new ThingsBean();
		things.setId(id);
		things.setFeature(feature);
		things.setTime(time);
		things.setPlace(place);
		things.setCampus(campus);
		things.setType(type);
		things.setDescription(description);
		things.setName(name);
		things.setEmail(email);
		things.setPhone(phone);
		findService.addThings(things);
		
		String realPath=request.getSession().getServletContext().getRealPath("/");
        String[] list1=realPath.split("/");
        realPath = "";
        for(int i=0;i<list1.length;i++){
        	list1[i]=list1[i]+"\\";
        	realPath=realPath+list1[i];
        }
        realPath=realPath.substring(0, realPath.length()-1);
        
        realPath = realPath + "images\\" + id + ".jpg";
        if(!file.isEmpty()){
        	try{	        			        	
        		byte[] bytes = file.getBytes();
        		if(bytes.length!=0)
        		{	        			        				        					        		
        			//File myFile=new File(s);	        				        		
        			File myTfile=new File(realPath);
	        		if(myTfile.exists()){
	        		
	        			myTfile.delete();
	        			myTfile.createNewFile();	        			
	        		}
        			out=new FileOutputStream(realPath);	  	                              			
        			out.write(bytes);
        			out.close();	
        		    
        			
        		}	  
             }catch(Exception e)  {
            	 
             }
        }   	
		map.put("success", "ghg");
		JSONObject json=new JSONObject(map);
		return json.toString();
	}
	//核对用户输入的验证码是否合法
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
