package com.petti.domain.free_board;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class FreeReplyVO {
	private Long rno; 
	private Long bno; 
	private String reply; 
	private String replyer; 

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime replyDate; 
		
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private  LocalDateTime updateDate;
}
