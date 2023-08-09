package com.petti.repository;

import java.util.List;

import com.petti.domain.board.AnnounceVO;

public interface BoardRepository {

	List<AnnounceVO> getList();
	
	void insert(AnnounceVO vo);

	Integer insertSelectKey(AnnounceVO vo);
	
	AnnounceVO read(Long bno);
	
	int delete(Long bno);
	
	int update(AnnounceVO vo);
}
