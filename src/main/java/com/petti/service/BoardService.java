package com.petti.service;

import java.util.List;

import com.petti.domain.AnnounceVO;

public interface BoardService {

	List<AnnounceVO> getList(); // 게시판 목록
	
	void register(AnnounceVO board); // 등록
	
	AnnounceVO get(Long bno); // 조회
	
	boolean modify(AnnounceVO board); // 수정
	
	boolean remove(Long bno); // 삭제
}
