package com.nowcoder.project.model.enums;

public enum BookStatusEnum {

	NORMAL(0), //正常
	DELETE(1), //删除
	RECOMMENDEN(2), //推荐
	;
	private int value;
	
	BookStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
