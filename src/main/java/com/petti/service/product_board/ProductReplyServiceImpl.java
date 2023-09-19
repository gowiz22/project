package com.petti.service.product_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductReplyVO;
import com.petti.domain.product_board.ReviewPageDTO;
import com.petti.repository.product_board.ProductBoardRepository;
import com.petti.repository.product_board.ProductReplyRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ProductReplyServiceImpl implements ProductReplyService {

	@Autowired
	private ProductReplyRepository replyRepository;
	
	@Override
	@Transactional
	public int register(ProductReplyVO vo) {
		if(replyRepository.insert(vo) == 1) {
			if(replyRepository.updateRate(vo.getPno()) == 1){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public ProductReplyVO get(Long rno) {
		return replyRepository.read(rno);
	}

	@Override
	@Transactional
	public int modify(ProductReplyVO vo) {
		if(replyRepository.update(vo) == 1) {
			vo.setPno(replyRepository.read(vo.getRno()).getPno());
			if(replyRepository.updateRate(vo.getPno()) == 1){
				return 1;
			}
		}
		return 0;
	}

	@Override
	@Transactional
	public int remove(Long rno) {
		ProductReplyVO vo = replyRepository.read(rno);
		if(replyRepository.delete(rno) == 1) {
			if(replyRepository.updateRate(vo.getPno()) == 1){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public ReviewPageDTO getList(Criteria criteria, Long pno) {
		return new ReviewPageDTO(
				replyRepository.getReplyCount(pno), 
				replyRepository.getList(pno, criteria));
	}

	@Override
	public List<String> getReviewerList(Long pno) {
		return replyRepository.getReviewerList(pno);
	}

}
