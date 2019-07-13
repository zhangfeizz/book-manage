package com.nowcoder.project.model;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Ticket {

	private int id;
	/**
	 * 相绑定的userId
	 */
	private int userId;
	/**
	 * t票实体
	 */
	private String ticket;
	/**
	 * 过期时间
	 */
	private Date expiredAt;

}
