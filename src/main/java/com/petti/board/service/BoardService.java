package com.petti.board.service;

import java.util.List;

import com.petti.board.domain.BoardVO;

public interface BoardService {

	List<BoardVO> getList(); // 게시판 목록
	
	void register(BoardVO board); // 등록
	
	BoardVO get(Long bno); // 조회
	
	boolean modify(BoardVO board); // 수정
	
	boolean remove(Long bno); // 삭제
}
