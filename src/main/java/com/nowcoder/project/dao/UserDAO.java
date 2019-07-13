package com.nowcoder.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nowcoder.project.model.User;

@Mapper
public interface UserDAO {

	String table_name = " user ";
	String insert_field = " name, email, password ";
	String select_field = " id, " + insert_field;

	@Insert({"insert into",table_name, "(", insert_field, ") values (#{name}, #{email} ,#{password}"})
	int addUser(User user);

	@Select({"select", select_field, "from", table_name, "where email = #{email}"})
	User selectByEmail(String email);

	@Select({"select", select_field, "from", table_name, "where id=#{uid}"})
	User selectById(int uid);


}
