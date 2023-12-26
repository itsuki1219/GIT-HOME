package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.member;

@Mapper
public interface memberMapper {
	@Insert("insert into member(name,username,password)"
			+ "values(#{name},#{username},#{password})")
	void add(member m);
	
	@Select("select * from member where username=#{username} and password=#{password}")
	member queryMember(String username,String password);
	
	
	@Select("select * from member where username=#{username}")
	member queryUsername(String username);
	
	@Select("select * from member where id=#{id}")
	member queryId(int id);
	
	@Update("update member set password=#{password} where id=#{id}")
	void updatePasswordById(@Param("id") int id, @Param("password") String password);
	
	@Delete("delete from member where id=#{id}")
	void delete(int id);
	
	
}
