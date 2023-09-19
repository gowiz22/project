package com.petti.repository.free_board;

import java.util.List;

import com.petti.domain.free_board.FreeLikeDTO;

public interface FreeBoardLikeRepository {

	void insert(FreeLikeDTO likeDTO);
	
	void delete(FreeLikeDTO likeDTO);
	
	FreeLikeDTO get(FreeLikeDTO likeDTO);
	
	List<String> findLikeUser(Long bno);
}
