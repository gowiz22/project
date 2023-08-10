package com.petti.repository.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnoReplyVO;

public interface AnnoReplyRepository {
	int insert(AnnoReplyVO vo);
	
	AnnoReplyVO read(Long rno);
	
	int delete(Long rno);
	
	int update(AnnoReplyVO vo);
	
	List<AnnoReplyVO> getList(
			@Param("bno") Long bno,
			@Param("criteria") Criteria criteria);
}
