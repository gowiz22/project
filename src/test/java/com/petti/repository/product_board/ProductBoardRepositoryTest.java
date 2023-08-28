package com.petti.repository.product_board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.petti.board.AppTest;
import com.petti.domain.product_board.ProductLikeDTO;
import com.petti.domain.product_board.ProductVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ProductBoardRepositoryTest extends AppTest {

	@Autowired
	ProductBoardRepository boardRepository;

	@Autowired
	ProductLikeRepository likeRepository;
	
	@Test
	@Ignore
	public void testGetList() {
//		List<ProductVO> list = boardRepository.getList();
//		log.info(list);
	}

	@Test
	@Ignore
	public void testinsert() {
		ProductVO vo = new ProductVO();
		vo.setP_name("제품 추가");
		vo.setDetail("제품 상세");
		vo.setWriter("admin");
		boardRepository.insert(vo);
	}

	@Test
	@Ignore
	public void testinsertSelectKey() {
		ProductVO vo = new ProductVO();
		vo.setP_name("제품 추가3");
		vo.setDetail("제품 상세3");
		vo.setWriter("admin");
		Integer key = boardRepository.insertSelectKey(vo);
		log.info(key);
	}

	@Test
	@Ignore
	public void testread() {
		ProductVO list = boardRepository.read(3L);
		log.info(list);
	}

	@Test
	@Ignore
	public void testdelete() {
		int delete = boardRepository.delete(5L);
		log.info(delete);
	}

	@Test
	@Ignore
	public void testupdate() {
		ProductVO vo = new ProductVO();
		vo.setP_name("수정 제품");
		vo.setDetail("수정 내용");
		vo.setPno(3L);
		int update = boardRepository.update(vo);
		log.info(update);
	}

	@Test
	@Ignore
	public void testInsertscore() {
		likeRepository.insert(new ProductLikeDTO(3L, 80, "cooicooi"));
		likeRepository.insert(new ProductLikeDTO(3L, 30, "cooicooi"));
		likeRepository.insert(new ProductLikeDTO(3L, 50, "cooicooi"));
		
	}
	@Test
//	@Ignore
	@Transactional
	public void testSelectScore() {
		double rate = likeRepository.getScoreRate(3L);
			boardRepository.updateScoreRate(3L, 50);
		
	}
	
}
