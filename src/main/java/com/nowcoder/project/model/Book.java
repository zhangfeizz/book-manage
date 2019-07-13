package com.nowcoder.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Book {

	private int id;

	private String name;

	private String author;

	private String price;

	/**
	 * {@link com.nowcoder.project.model.enums.BookStatusEnum}
	 */
	private int status;

}
