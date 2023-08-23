package com.petti.repository.free_board;

import java.util.List;

import com.petti.domain.free_board.FreeBoardAttachVO;

public interface FreeBoardAttachRepository {

	void insert(FreeBoardAttachVO vo);
	
	void delete(String uuid);
	
	List<FreeBoardAttachVO> selectByBno(Long bno);
	
	FreeBoardAttachVO selectByUuid(String uuid);
	
}
