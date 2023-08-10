package com.petti.domain.free_board;

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
@Alias("freeVO")
public class FreeBoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String category;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
