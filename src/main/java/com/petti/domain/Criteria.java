package com.petti.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class Criteria {
	private int pageNum; // 현재 페이지 
	private int amount; // 한 페이지당 게시물 수
	
	public Criteria() {
		this(1,10); // 1페이지, 한 페이지당 게시물 수는 10개 
	}
	
	public int getMaxRow() { // #{maxRow}
		return pageNum*amount;
	}
	
	public int getMinRow() { // #{minRow}
		return (pageNum-1)*amount;
	}
}