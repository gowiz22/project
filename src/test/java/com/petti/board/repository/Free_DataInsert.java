package com.petti.board.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.repository.free_board.FreeBoardRepository;

public class Free_DataInsert extends AppTest{
	
	@Autowired
	FreeBoardRepository repository; 
	
	
	@Test
//	@Ignore
	public void test() {
		
		for(int i=1;i<=212;i++) {
			int cno = (int) ((Math.random()*4)+1);
			int recommend = (int) ((Math.random()*8)+1);
			
			FreeBoardVO vo = FreeBoardVO.builder()
					.title("테스트 데이터 " + i)
					.content("내용 : 자바 오라클 " + i)
					.writer("작성자" + (i%5))
					.cno(cno)
					.likeCount(recommend)
					.build();
			repository.testinsert(vo);			
		}
		
//		for(int i=1;i<=212;i++) {
//			int cno = (int) ((Math.random()*4)+1);
//			int recommend = (int) ((Math.random()*10)+1);
//
//			FreeBoardVO vo = FreeBoardVO.builder()
//					.title("제목 : 오라클 " + i)
//					.content("내용 : 정보처리기사 " + i)
//					.writer("글쓴이" + (i%5))
//					.cno(cno)
//					.likeCount(recommend)
//					.build();
//			repository.testinsert(vo);			
//		}
//		
//		for(int i=1;i<=212;i++) {
//			int cno = (int) ((Math.random()*4)+1);
//			int recommend = (int) ((Math.random()*10)+1);
//
//			FreeBoardVO vo = FreeBoardVO.builder()
//					.title("제목 : 자바 " + i)
//					.content("내용 : 스프링 정보처리기사 " + i)
//					.writer("관리자" + (i%5))
//					.cno(cno)
//					.likeCount(recommend)
//					.build();
//			repository.testinsert(vo);			
//		}
//		
//		for(int i=1;i<=212;i++) {
//			int cno = (int) ((Math.random()*4)+1);
//			int recommend = (int) ((Math.random()*10)+1);
//
//			FreeBoardVO vo = FreeBoardVO.builder()
//					.title("제목 : 테스트 데이터 " + i)
//					.content("내용 : 스프링부트 " + i)
//					.writer("스프링" + (i%5))
//					.cno(cno)
//					.likeCount(recommend)
//					.build();
//			repository.testinsert(vo);			
//		}
	}
	
	@Test
	@Ignore
	public void insert() {
		for(int i=1;i<=23;i++) {
			int recommend = (int) ((Math.random()*10)+1);

			FreeBoardVO vo = FreeBoardVO.builder()
					.title("제목 : 테스트 데이터 " + i)
					.content("내용 : 스프링부트 " + i)
					.writer("스프링" + (i%5))
					.cno(24)
					.likeCount(recommend)
					.build();
			repository.testinsert(vo);			
		}
	}
}