package com.petti.repository.product_board;

import java.util.List;

import com.petti.domain.free_board.FreeLikeDTO;
import com.petti.domain.product_board.ProductLikeDTO;

public interface ProductLikeRepository {

	void insert(ProductLikeDTO likeDTO);
	
	void delete(ProductLikeDTO likeDTO);
	
	void update(ProductLikeDTO likeDTO);
	
	FreeLikeDTO get(ProductLikeDTO likeDTO);
	
	List<String> findLikeUser(ProductLikeDTO pno);
	
	double getScoreRate(Long pno);
}
