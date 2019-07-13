package com.nowcoder.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class User {
	private int id;

	private String name;

	private String email;

	/**
	 * 经过md5加密
	 */
	private String password;
	
}
