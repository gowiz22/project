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
	private Long cno;
	private String kind;
	private Long count;
}
