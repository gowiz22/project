package com.petti.repository.board;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.Criteria;
import com.petti.domain.board.AnnoReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class AnnoReplyRepositoryTest extends AppTest{

	@Autowired
	AnnoReplyRepository replyRepository;
	
	@Test
	@Ignore
	public void insertTest() {
		IntStream.rangeClosed(1, 10).forEach(i->{			
			AnnoReplyVO vo = AnnoReplyVO.builder()
					.bno(1L)
					.reply("댓글 테스트"+i)
					.replyer("사용자"+i)
					.build();
			replyRepository.insert(vo);
		});
	}

	@Test
	@Ignore
	public void readTest() {
		AnnoReplyVO vo = replyRepository.read(1L);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void updateTest() {
		AnnoReplyVO vo = new AnnoReplyVO();
		vo.setReply("댓글 테스트 -- 수정");
		vo.setRno(2L);
		replyRepository.update(vo);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		replyRepository.delete(3L);
	}
	
	@Test
	@Ignore
	public void getListTest() {
		replyRepository.getList(1L, new Criteria())
			.forEach(r -> log.info(r));
	}
}

