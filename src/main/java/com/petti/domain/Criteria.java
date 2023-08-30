package com.petti.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Criteria{
	private int pageNum; // 현재 페이지 
	private int amount; // 한 페이지당 게시물 수
	
	private String type;
	private String keyword;
	private String kind;
	private int cno;
	private String recommend = "F";
	private int radio = 0;
	
	public Criteria() {
		this(1,10); // 1페이지, 한 페이지당 게시물 수는 10개 
	}

	public Criteria (int cno) {
		this.cno= cno;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getMaxRow() { // #{maxRow}
		return pageNum*amount;
	}
	
	public int getMinRow() { // #{minRow}
		return (pageNum-1)*amount;
	}
	
	public int getRadio() { // #{radio}
		return this.radio;
	}
	
	public String[] getTypes() { // collection="types"
		return type == null ? new String[] {} : type.split("");
	}
	
}