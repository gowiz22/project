package com.petti.repository.board;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.free_board.FreeBoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class FreeBoardRepositoryTest extends AppTest{

	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Test
//	@Ignore
	public void testGetList() {
		List<FreeBoardVO> list = boardRepository.getList();
		log.info(list);
	}

}
