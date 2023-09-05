package com.petti.domain.free_board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CategoryVO {
	private int cno;
	private String kind;
	private int count;
}
