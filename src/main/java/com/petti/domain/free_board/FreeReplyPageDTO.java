package com.petti.domain.free_board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class FreeReplyPageDTO {
	private int replyCount;
	private List<FreeReplyVO> list;
}
