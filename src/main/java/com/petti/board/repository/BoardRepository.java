package com.petti.board.repository;

import java.util.List;

import com.petti.board.domain.BoardVO;

public interface BoardRepository {

	List<BoardVO> getList();
	
	void insert(BoardVO board);
	
	BoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(BoardVO board);
}
