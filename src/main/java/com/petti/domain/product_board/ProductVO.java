package com.petti.domain.product_board;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("productVO")
public class ProductVO {
	private Long pno;
	private String p_name;
	private String detail;
	private String writer;
	private Double recommendation;
	
	@DateTimeFormat(pattern = "yyyy년MM월dd일 HH시mm분")
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy년MM월dd일 HH시mm분")
	private LocalDateTime updateDate;
}
