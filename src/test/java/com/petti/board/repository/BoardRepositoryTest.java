package com.petti.board.repository;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.Criteria;
import com.petti.domain.board.AnnounceVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.repository.board.AnnoBoardRepository;
import com.petti.repository.free_board.FreeBoardRepository;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardRepositoryTest extends AppTest{

	@Autowired
	private AnnoBoardRepository boardRepository;
	
	@Autowired
	FreeBoardRepository freeBoardRepository; 
	
	@Test
//	@Ignore
	public void testGetList() {
//		List<AnnounceVO> list = boardRepository.getList();
//		log.info(list);
		List<FreeBoardVO> list = freeBoardRepository.getList(new Criteria());
		list.forEach(b->System.out.println(b));
	
	}

	@Test
	@Ignore
	public void testRead() {
		AnnounceVO read = boardRepository.read(1L);
		log.info(read);
	}
	
	
	@Test
	@Ignore
	public void testInsert() {
		AnnounceVO vo = AnnounceVO.builder()
				.title("새로 작성하는 글...")
				.content("새로 작성하는 글 내용")
				.writer("관리자")
				.build();
		boardRepository.insert(vo);
		log.info(vo);
	}

	@Test
	@Ignore
	public void testdelete() {
		int delete = boardRepository.delete(2L);
		log.info(delete);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		AnnounceVO vo = AnnounceVO.builder()
				.bno(1L)
				.title("수정제목")
				.content("수정 내용")
				.build();
		int update = boardRepository.update(vo);
		
		log.info(update);
	}
}
