package com.petti.repository.product_board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductReplyVO;

public interface ProductReplyRepository {
	int insert(ProductReplyVO vo);
	
	ProductReplyVO read(Long rno);
	
	int delete(Long rno);
	
	int update(ProductReplyVO vo);
	
	List<ProductReplyVO> getList(
			@Param("pno") Long pno,
			@Param("criteria") Criteria criteria);
	
	int getReplyCount(Long pno);
	
	void deletePno(Long pno);
	
	int updateRate(Long pno);
}
