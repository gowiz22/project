package com.petti.service;

import java.util.List;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnounceVO;

public interface AnnoBoardService {

	List<AnnounceVO> getList(Criteria criteria); // 게시판 목록
	
	void register(AnnounceVO board); // 등록
	
	AnnounceVO get(Long bno); // 조회
	
	boolean modify(AnnounceVO board); // 수정
	
	boolean remove(Long bno); // 삭제
	
	int totalCount();
}
