package com.petti.service.board;

import java.util.List;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnoReplyVO;

	public interface AnnoReplyService {
		
		int register(AnnoReplyVO vo);
		
		AnnoReplyVO get(Long rno);
		
		int modify(AnnoReplyVO vo);
		
		int remove(Long rno);
		
		List<AnnoReplyVO> getList(Criteria criteria, Long bno);
}
