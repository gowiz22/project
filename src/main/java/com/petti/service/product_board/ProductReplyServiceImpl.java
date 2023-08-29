package com.petti.service.product_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductReplyVO;
import com.petti.domain.product_board.ProductVO;
import com.petti.domain.product_board.ReviewPageDTO;
import com.petti.repository.product_board.ProductReplyRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ProductReplyServiceImpl implements ProductReplyService {

	@Autowired
	private ProductReplyRepository replyRepository;
	
	@Override
	public int register(ProductReplyVO vo) {
		return replyRepository.insert(vo);
	}

	@Override
	public ProductReplyVO get(Long rno) {
		return replyRepository.read(rno);
	}

	@Override
	public int modify(ProductReplyVO vo) {
		return replyRepository.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return replyRepository.delete(rno);
	}

	@Override
	public ReviewPageDTO getList(Criteria criteria, Long pno) {
		return new ReviewPageDTO(
				replyRepository.getReplyCount(pno), 
				replyRepository.getList(pno, criteria));
	}

}
