package com.petti.service.product_board;

import java.util.List;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductReplyVO;
import com.petti.domain.product_board.ReviewPageDTO;

public interface ProductReplyService {
	
	int register(ProductReplyVO vo);
	
	ProductReplyVO get(Long rno);
	
	int modify(ProductReplyVO vo);
	
	int remove(Long rno);
	
	ReviewPageDTO getList(Criteria criteria, Long pno);
	
	List<String> getReviewerList(Long pno);
}
