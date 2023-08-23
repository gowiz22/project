package com.petti.repository.product_board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductVO;

public interface ProductBoardRepository {

	List<ProductVO> getList(Criteria criteria);
	
	void insert(ProductVO vo);
	
	Integer insertSelectKey(ProductVO vo);
	
	ProductVO read(Long pno);
	
	int delete(Long pno);
	
	int update(ProductVO vo);

	List<ProductVO> getList( // 페이징 처리
			@Param("pageNum") int pageNum,
			@Param("amount") int amount);
	
	int getTotalCount(Criteria criteria); // 총 상품 수
}
