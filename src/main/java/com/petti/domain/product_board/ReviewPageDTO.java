package com.petti.domain.product_board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReviewPageDTO {
	private int replyCount;
	private List<ProductReplyVO> list;
}
