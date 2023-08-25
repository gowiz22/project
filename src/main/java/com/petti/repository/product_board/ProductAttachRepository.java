package com.petti.repository.product_board;

import java.util.List;

import com.petti.domain.product_board.ProductBoardAttachVO;

public interface ProductAttachRepository {

	void insert(ProductBoardAttachVO vo);
	
	void delete(String uuid);
	
	List<ProductBoardAttachVO> selectAll();

	List<ProductBoardAttachVO> selectByPno(Long pno);
	
	ProductBoardAttachVO selectByUuid(String uuid);

	void deleteAll(Long pno);
	
}
