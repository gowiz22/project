package com.petti.repository.board;

import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeReplyVO;
import com.petti.repository.free_board.FreeReplyRepository;

import lombok.extern.log4j.Log4j;

@Log4j
public class FreeReplyRepositoryTest extends AppTest{

	@Autowired
	FreeReplyRepository replyRepository;
	
	@Test
	@Ignore
	public void insertTest() {
		IntStream.rangeClosed(1, 10).forEach(i->{			
			FreeReplyVO vo = FreeReplyVO.builder()
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
		FreeReplyVO vo = replyRepository.read(1L);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void updateTest() {
		FreeReplyVO vo = new FreeReplyVO();
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

