package com.petti.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnoReplyVO;
import com.petti.repository.board.AnnoReplyRepository;

@Service
public class AnnoReplyServiceImpl implements AnnoReplyService {

	@Autowired
	private AnnoReplyRepository replyRepository;
	
	@Override
	public int register(AnnoReplyVO vo) {
		return replyRepository.insert(vo);
	}

	@Override
	public AnnoReplyVO get(Long rno) {
		return replyRepository.read(rno);
	}

	@Override
	public int modify(AnnoReplyVO vo) {
		return replyRepository.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return replyRepository.delete(rno);
	}

	@Override
	public List<AnnoReplyVO> getList(Criteria criteria, Long bno) {
		return replyRepository.getList(bno, criteria);
	}

}
