package com.petti.repository.free_board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.free_board.FreeBoardVO;

public interface FreeBoardRepository {

	List<FreeBoardVO> getList(Criteria criteria);
	
	void insert(FreeBoardVO vo);

	void testinsert(FreeBoardVO vo);
	
	Integer insertSelectKey(FreeBoardVO vo);
	
	FreeBoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(FreeBoardVO vo);
	
	// 전체 게시물 수 
	int getTotalCount(Criteria criteria);
	
	// 카테고리 리스트 
	List<CategoryVO> category();
	
	// 댓글 수 변화
	void updatedReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
	// 추천수 업데이트
	void updateLikeCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
	// 카테고리별 게시글 리스트
	List<Long> boardInCategory(Long cno);
	
	// 조회수 업데이트
	void updateCount(Long bno);
	
}
