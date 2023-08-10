package com.petti.repository.free_board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeBoardVO;

public interface FreeBoardRepository {

	List<FreeBoardVO> getList(Criteria criteria);
	
	void insert(FreeBoardVO vo);
	
	Integer insertSelectKey(FreeBoardVO vo);
	
	FreeBoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(FreeBoardVO vo);
	
	List<FreeBoardVO> getList(
			@Param("pageNum") int pageNum,
			@Param("amount") int amount);
	
	int getTotalCount(Criteria criteria);
}
