package com.petti.repository.product_board;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.domain.product_board.ProductVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ProductBoardRepositoryTest extends AppTest {

	@Autowired
	ProductBoardRepository boardRepository;

	@Autowired
	ProductReplyRepository replyRepository;
	
	@Test
	@Ignore
	public void testGetList() {
//		List<ProductVO> list = boardRepository.getList();
//		log.info(list);
	}

	@Test
//	@Ignore
	public void testinsert() {
			for(int i=1;i<=41;i++) {
				int price = (int) ((Math.random()*99)+1)*100;
				double recommend = Math.ceil((Math.random()*99)+1)/10.0;
				
				ProductVO vo = ProductVO.builder()
						.p_name("장난감 " + i)
						.detail("장난감 상세 " + i)
						.writer("작성자" + (i%5))
						.price(price)
						.scoreRate(recommend)
						.build();
				boardRepository.testinsert(vo);			
			}
			for(int i=1;i<=41;i++) {
				int price = (int) ((Math.random()*100)+1)*100;
				double recommend = Math.ceil((Math.random()*99)+1)/10.0;
				
				ProductVO vo = ProductVO.builder()
						.p_name("사료 " + i)
						.detail("사료 상세 " + i)
						.writer("작성자" + (i%5))
						.price(price)
						.scoreRate(recommend)
						.build();
				boardRepository.testinsert(vo);			
			}
			for(int i=1;i<=41;i++) {
				int price = (int) ((Math.random()*100)+1)*100;
				double recommend = Math.ceil((Math.random()*99)+1)/10.0;
				
				ProductVO vo = ProductVO.builder()
						.p_name("미용 " + i)
						.detail("미용 " + i)
						.writer("작성자" + (i%5))
						.price(price)
						.scoreRate(recommend)
						.build();
				boardRepository.testinsert(vo);			
			}
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

}
