package com.petti.repository.free_board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeReplyVO;

public interface FreeReplyRepository {
	int insert(FreeReplyVO vo);
	
	FreeReplyVO read(Long rno);
	
	int delete(Long rno);
	
	int update(FreeReplyVO vo);
	
	List<FreeReplyVO> getList(
			@Param("bno") Long bno,
			@Param("criteria") Criteria criteria);
	
	int getReplyCount(Long bno);
	
	void deleteBno(Long bno);
}
