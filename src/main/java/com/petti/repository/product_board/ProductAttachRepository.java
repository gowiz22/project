package com.petti.repository.product_board;

import java.util.List;

import com.petti.domain.product_board.ProductBoardAttachVO;

public interface ProductAttachRepository {

	void insert(ProductBoardAttachVO vo);
	
	void delete(String uuid);
	
	List<ProductBoardAttachVO> selectByBno(Long bno);
	
	ProductBoardAttachVO selectByUuid(String uuid);

	void deleteAll(Long bno);
	
}
