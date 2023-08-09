package com.petti.board.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.board.AnnounceVO;
import com.petti.repository.AnnoBoardRepository;

public class DataInsert extends AppTest{
	
	@Autowired
	AnnoBoardRepository repository; 
	
	
	// 408개 데이터 삽입 총 412개의 게시물
	@Test
	public void test() {
		
		for(int i=1;i<=408;i++) {
			AnnounceVO vo = AnnounceVO.builder()
					.title("제목 : 페이징 처리 " + i)
					.content("내용 : 페이징 처리 " + i)
					.writer("작성자" + (i%5))
					.build();
			repository.insert(vo);			
		}
	}
}