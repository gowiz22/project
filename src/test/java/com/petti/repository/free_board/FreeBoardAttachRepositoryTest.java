package com.petti.repository.free_board;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.petti.board.AppTest;
import com.petti.domain.free_board.FreeBoardAttachVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class FreeBoardAttachRepositoryTest extends AppTest{

	@Autowired
	FreeBoardAttachRepository boardAttachRepository;
	
	@Test
	@Ignore
	public void test() {
		FreeBoardAttachVO vo = new FreeBoardAttachVO();
		vo.setBno(1L);
		vo.setFileName("test02.txt");
		vo.setFileType(false);
		vo.setUploadPath("c:/upload");
		String uuid = UUID.randomUUID().toString();
		vo.setUuid(uuid);
		boardAttachRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testSelectByBno() {
		boardAttachRepository.selectByBno(1L)
			.forEach(file -> log.info(file));
	}
	
	@Test
	@Ignore
	public void testDelete() {
		// 데이터베이스에 저장된 uuid값을 참고
		String uuid = "8bfed15a-2f91-4672-bd95-fe890f774600";
		boardAttachRepository.delete(uuid);
	}

}
