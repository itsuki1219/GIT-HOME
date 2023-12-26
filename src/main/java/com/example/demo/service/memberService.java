package com.example.demo.service;

import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.member;

public interface memberService {
	void addMember(member m);
	member Login(String username,String password);
	boolean UsernameRepeat(String username);
	
	
	
	void updatePassword(member m);
	void delete(int id);
}
