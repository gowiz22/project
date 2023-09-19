package com.petti.service.free_board;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeReplyPageDTO;
import com.petti.domain.free_board.FreeReplyVO;

public interface FreeReplyService {
	
	int register(FreeReplyVO vo);
	
	FreeReplyVO get(Long rno);
	
	int modify(FreeReplyVO vo);
	
	int remove(Long rno);
	
	FreeReplyPageDTO getList(Criteria criteria, Long bno);
}
