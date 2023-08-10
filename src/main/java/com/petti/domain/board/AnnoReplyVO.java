package com.petti.domain.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnnoReplyVO {
	private Long rno; 
	private Long bno; 
	private String reply; 
	private String replyer; 
	private LocalDateTime replyDate; 
	private LocalDateTime updateDate;
}
