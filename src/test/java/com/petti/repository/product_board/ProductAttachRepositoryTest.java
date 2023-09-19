package com.petti.repository.product_board;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.product_board.ProductBoardAttachVO;

public class ProductAttachRepositoryTest extends AppTest{

	@Autowired
	ProductAttachRepository attachRepository;
	
	@Test
	public void test() {
		ProductBoardAttachVO vo = new ProductBoardAttachVO();
		vo.setPno(1L);
		vo.setFileName("test02.txt");
		vo.setFileType(false);
		vo.setUploadPath("c:/upload");
		String uuid = UUID.randomUUID().toString();
		vo.setUuid(uuid);
		attachRepository.insert(vo);
	}

}
