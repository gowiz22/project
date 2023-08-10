package com.petti.repository.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnounceVO;

public interface AnnoBoardRepository {

	List<AnnounceVO> getList(Criteria criteria);
	
	void insert(AnnounceVO vo);

	Integer insertSelectKey(AnnounceVO vo);
	
	AnnounceVO read(Long bno);
	
	int delete(Long bno);
	
	int update(AnnounceVO vo);
	
	List<AnnounceVO> getList( // 페이징 처리
			@Param("pageNum") int pageNum,
			@Param("amount") int amount);
	
	int getTotalCount(Criteria criteria); // 전체 게시물 수
}
