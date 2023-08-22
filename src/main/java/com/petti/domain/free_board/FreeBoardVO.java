package com.petti.domain.free_board;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

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
	private int cno;
	private int replyCnt;
	private int likeCount;
	
	@DateTimeFormat(pattern = "yyyy년MM월dd일 HH시mm분")
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy년MM월dd일 HH시mm분")
	private LocalDateTime updateDate;
	
	private List<FreeBoardAttachVO> attachList;
	
}
