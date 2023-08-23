package com.petti.service.product_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.Criteria;
import com.petti.domain.product_board.ProductVO;
import com.petti.repository.product_board.ProductBoardRepository;

@Service
public class ProductBoardServiceImpl implements ProductBoardService {

	@Autowired
	private ProductBoardRepository boardRepository;
	
	
	@Override
	public List<ProductVO> getList(Criteria criteria) {
		return boardRepository.getList(criteria);
	}

	@Override
	public ProductVO get(Long pno) {
		return boardRepository.read(pno);
	}

	
	@Override
	public void register(ProductVO vo) {
		boardRepository.insert(vo);
	}

	@Override
	public boolean modify(ProductVO vo) {
		return boardRepository.update(vo)==1;
	}

	@Override
	public boolean remove(Long pno) {
		return boardRepository.delete(pno)==1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardRepository.getTotalCount(criteria);
	}

}
