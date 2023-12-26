package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.member;
import com.example.demo.vo.porder;

@Mapper
public interface porderMapper {
	@Insert("insert into porder(name,A,B,C)"
			+ "values(#{name},#{A},#{B},#{C})")
	void add(porder p);
	
	@Select("select * from porder ")
	List<porder> SelectAll();
	
	@Select("select * from porder where name=#{name}")
	List<porder>  SelectName(String name);
	
	//@Select("select * from member where username=#{username}")
	//member queryUsername(String username);
}
