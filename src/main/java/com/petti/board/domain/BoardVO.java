package com.petti.board.domain;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("board")
public class BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
