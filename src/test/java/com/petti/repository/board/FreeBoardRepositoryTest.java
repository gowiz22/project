package com.petti.repository.board;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.Criteria;
import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.repository.free_board.FreeBoardRepository;

import lombok.extern.log4j.Log4j;

@Log4j
public class FreeBoardRepositoryTest extends AppTest{

	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Test
//	@Ignore
	public void testGetList() {
		Criteria criteria = new Criteria();
//		criteria.setType("C");
//		criteria.setKeyword("자바");
		criteria.setCno(1);
		List<FreeBoardVO> list = boardRepository.getList(criteria);
		log.info(list);
	}
	
	@Test
	@Ignore
	public void testRead() {
		FreeBoardVO read = boardRepository.read(10L);
		log.info(read);
	}
	
	
	@Test
	@Ignore
	public void testInsert() {
		FreeBoardVO vo = FreeBoardVO.builder()
				.title("새로 작성하는 글...")
				.content("새로 작성하는 글 내용")
				.writer("관리자")
				.cno(2L)
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
		FreeBoardVO vo = FreeBoardVO.builder()
				.bno(3L)
				.title("수정제목")
				.content("수정 내용")
				.cno(3L)
				.build();
		int update = boardRepository.update(vo);
		log.info(update);
	}
	
	@Test
	@Ignore
	public void category() {
		List<CategoryVO> count = boardRepository.category();
		log.info(count);
		
	}
}
