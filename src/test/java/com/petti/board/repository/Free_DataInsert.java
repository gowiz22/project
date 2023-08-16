package com.petti.board.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.board.AnnounceVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.repository.board.AnnoBoardRepository;
import com.petti.repository.free_board.FreeBoardRepository;

public class Free_DataInsert extends AppTest{
	
	@Autowired
	FreeBoardRepository repository; 
	
	
	@Test
	public void test() {
		
		for(int i=1;i<=212;i++) {
			FreeBoardVO vo = FreeBoardVO.builder()
					.title("제목 : 스프링 정보처리기사 " + i)
					.content("내용 : 자바 오라클 " + i)
					.writer("작성자" + (i%5))
					.cno(1)
					.build();
			repository.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			FreeBoardVO vo = FreeBoardVO.builder()
					.title("제목 : 오라클 " + i)
					.content("내용 : 정보처리기사 " + i)
					.writer("글쓴이" + (i%5))
					.cno(2)
					.build();
			repository.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			FreeBoardVO vo = FreeBoardVO.builder()
					.title("제목 : 자바 " + i)
					.content("내용 : 스프링 정보처리기사 " + i)
					.writer("관리자" + (i%5))
					.cno(3)
					.build();
			repository.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			FreeBoardVO vo = FreeBoardVO.builder()
					.title("제목 : 테스트 데이터 " + i)
					.content("내용 : 스프링부트 " + i)
					.writer("스프링" + (i%5))
					.cno(4)
					.build();
			repository.insert(vo);			
		}
	}
}