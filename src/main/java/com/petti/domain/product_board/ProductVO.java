package com.petti.domain.product_board;

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
@Alias("productVO")
@Builder
public class ProductVO {
	private Long pno;
	private String p_name;
	private String detail;
	private String writer;
	private int price;
	private Double scoreRate;
	
	@DateTimeFormat(pattern = "yyyy년MM월dd일 HH시mm분")
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy년MM월dd일 HH시mm분")
	private LocalDateTime updateDate;
	
	private List<ProductBoardAttachVO> attachList;
	
	private double CeilingScore() {
		if (scoreRate != null) {
			scoreRate = Math.round(scoreRate * 100) / 100.0;
		}
		return scoreRate;
	}
}
