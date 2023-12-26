package com.example.demo.controller;



import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.example.demo.service.impl.memberServiceImpl;
import com.example.demo.vo.member;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class memberController {
	@Autowired
	memberServiceImpl msi;
	
	@Autowired 
	HttpSession session;
	
	@Autowired
	HttpServletResponse response;
	@PostMapping("/login")
	public ModelAndView gotoLogin(String username,String password)
	{
		member m=msi.Login(username, password);
		ModelAndView mav=null;
		if(m!=null)
		{
			session.setAttribute("M", m);
			mav=new ModelAndView("/loginSuccess");
		}
		else
		{
			mav=new ModelAndView("/loginError");
		}
		return mav;
		
		
	}
	
	@GetMapping("/addMember")
	public ModelAndView gotoAddMember()
	{
		return new ModelAndView("/addMember");
		
	}
	
	@PostMapping("/add")
	public ModelAndView add(String name, String username, String password) {
	    // 使用 StringUtils.isEmpty 檢查是否為空值或空字符串
	    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
	        // 處理空值的情況，返回一個錯誤頁面
	        ModelAndView mav = new ModelAndView("/addMemberError");
	        return mav;
	    }

	    // 檢查用戶名是否重複
	    boolean b = msi.UsernameRepeat(username);
	    ModelAndView mav;

	    if (b) {
	        mav = new ModelAndView("/addMemberError");
	    } else {
	        // 沒有重複且沒有空值，可以新增會員
	        member m = new member(name, username, password);
	        msi.addMember(m);
	        mav = new ModelAndView("/addMemberSuccess");
	    }

	    return mav;
	}
	
	@PostMapping("/updatePassword")
    public ModelAndView updatePassword(String password, String newPassword) {
        member m = (member) session.getAttribute("M");

        
            // 檢查新密碼是否為空值
            if (StringUtils.isEmpty(newPassword)) {
                return new ModelAndView("/updatePasswordError");
            }
            else {        // 檢查新舊密碼是否相同
            	if (m.getPassword().equals(newPassword)) {
                    return new ModelAndView("/updatePasswordError");
                }
            	else {
            		 m.setPassword(newPassword);
                     msi.updatePassword(m);

                     // 返回更新成功的頁面
                     return new ModelAndView("/updatePasswordSuccess");
				}
            }
			}
	@RequestMapping("/Rlogin")
	public ModelAndView Nindex() {
	   

	    // 清空 Session
	    session.removeAttribute("M");
	    

	    // 重定向到首頁
	    return new ModelAndView("redirect:/index.html");
	    }

	@RequestMapping("/deleteMember")
	public ModelAndView delete() {
	    member m = (member) session.getAttribute("M");
	    int id= m.getId();

	    msi.delete(id);

	    // 清空 Session
	    session.removeAttribute("M");
	    

	 // 重定向到首頁
	    return new ModelAndView("redirect:/index.html");
	}

        		
        	
            
     
      
       
		
    
	 @GetMapping("/Npassword")
		public ModelAndView gotoUpdatePassword()
		{
			return new ModelAndView("/updatePassword");
			
		}
	 
	 @GetMapping("/Ndelete")
		public ModelAndView gotoDelete()
		{
			return new ModelAndView("/deleteMember");
			
		}
	 
	}


	
	
	
	
	

