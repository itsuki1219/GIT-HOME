package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.memberMapper;
import com.example.demo.service.memberService;
import com.example.demo.vo.member;
@Service
public class memberServiceImpl implements memberService{
	@Autowired
	memberMapper mp;
	
	
	@Override
	public void addMember(member m) {
		mp.add(m);
		
	}

	@Override
	public member Login(String username, String password) {
		member m=mp.queryMember(username, password);
		
		return m;
	}

	@Override
	public boolean UsernameRepeat(String username) {
		member m=mp.queryUsername(username);
		boolean x=false;
		if(m!=null) x=true;
		return x;
	}

	@Override
	public void updatePassword(member m) {
		    int memberId = m.getId();
		    String newPassword = m.getPassword();

		    // 直接使用 queryId 方法來更新密碼
		    mp.updatePasswordById(memberId, newPassword);
	}

	@Override
	public void delete(int id) {
		mp.delete(id);
		
	}

	
}
