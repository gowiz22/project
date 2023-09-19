package com.petti.domain.product_board;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

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
@Alias("productReplyVO")
public class ProductReplyVO {
	private Long rno; 
	private Long pno; 
	private int score;
	private String r_comment; 
	private String reviewer; 
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime regDate; 
		
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private  LocalDateTime updateDate;
}
