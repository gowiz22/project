package com.petti.service.product_board;

import java.util.List;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductBoardAttachVO;
import com.petti.domain.product_board.ProductVO;

public interface ProductBoardService {

	List<ProductVO> getList(Criteria criteria); // 게시판 목록

	ProductVO get(Long pno);
	
	void register(ProductVO vo); // 등록
	
	boolean modify(ProductVO vo); // 수정
	
	boolean remove(Long pno); // 삭제
	
	int totalCount(Criteria criteria); // 상품 총 수
	
	List<ProductBoardAttachVO> getAttachList(Long pno);

	ProductBoardAttachVO getAttach(String uuid);

	List<ProductBoardAttachVO> getThumbnail();
}
