package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.porderService;
import com.example.demo.service.impl.memberServiceImpl;
import com.example.demo.service.impl.porderServiceImpl;
import com.example.demo.vo.member;
import com.example.demo.vo.porder;
import com.fasterxml.jackson.core.sym.Name;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/porder")
public class porderController {

	@Autowired
	porderServiceImpl psi;

	@Autowired

	HttpSession session;

	@Autowired
	HttpServletResponse response;

	@PostMapping("/add")
	public ModelAndView add(int a, int b, int c) {
		member m = (member) session.getAttribute("M");
		porder p = new porder(m.getName(), a, b, c);
		
		List<porder> l=(List<porder>) session.getAttribute("L");
		if (l == null) {
	        l = new ArrayList<>();
	    }
		 l.add(p);
		 session.setAttribute("L", l);
		 session.setAttribute("P", p);
		 
		return new ModelAndView("/porder/confirm");

	}


	   @GetMapping("/Nrecord")
	    @ResponseBody
	    public List<porder> selectByName() {
	        member m = (member) session.getAttribute("M");

	        if (m != null) {
	            String loggedInName = m.getName();
	            return psi.SelectName(loggedInName);
	        } else {
	            // 处理未登录的情况，例如返回空列表或其他提示信息
	            return new ArrayList<>();
	        }
	    }
	   
	



	
	@GetMapping("/porder")
	public ModelAndView gotoporder() {
		return new ModelAndView("/porder/porder");

	}
	
	@GetMapping("/confirm2")
	public ModelAndView gotoConfirm2() {
	    ModelAndView mav = new ModelAndView("/porder/confirm2");

	    List<porder> orders = (List<porder>) session.getAttribute("L");

	    double totalPrice = 0;
	    if (orders != null) {
	        totalPrice = orders.stream()
	            .filter(order -> order != null)
	            .mapToDouble(order -> order.getA() * 100 + order.getB() * 120 + order.getC() * 150)
	            .sum();
	    }

	    mav.addObject("totalPrice", totalPrice);
	    

	    return mav;
	}

	/*@GetMapping("/queryAll")
	public List<porder> queryAll()
	{
		return psi.findAll();
	}*/
	

	@RequestMapping("/finish")
	public ModelAndView gotofinish() {
		List<porder> orders = (List<porder>) session.getAttribute("L");
		
		return new ModelAndView("/porder/finish");
	}

	@GetMapping("/logout")
	public void gotologout() {
		session.removeAttribute("M");
		session.removeAttribute("P");
		session.removeAttribute("L");
		session.invalidate();
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
