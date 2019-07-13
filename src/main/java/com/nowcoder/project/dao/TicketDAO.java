package com.nowcoder.project.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nowcoder.project.model.Ticket;


@Mapper
public interface TicketDAO {

	String table_name = " ticket ";
	String insert_field = "user_id, ticket, expired_at";
	String select_field = "id, " + insert_field;
	
	@Insert({"insert into", table_name,"(", insert_field, ") values (#{userId}, #{ticked}, #{expiredAt})"})
	int addTicket(Ticket t);

	@Select({"select" ,select_field, "from", table_name ,"where user_id=#{uid}"})
	Ticket selctByUserId(int uid);

	@Delete({"delete from" , table_name, "where id=#{tid}"})
	void deleteTickedById(int tid);

	@Delete({"delete from" , table_name, "where ticket=#{t}"})
	void deleteTicket(String t);

	@Select({"select", select_field, "from", table_name, "where ticket=#{t}"})
	Ticket selectByTicket(String t);
	
	
	

}
