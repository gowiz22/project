package com.petti.service.free_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeReplyPageDTO;
import com.petti.domain.free_board.FreeReplyVO;
import com.petti.repository.free_board.FreeBoardRepository;
import com.petti.repository.free_board.FreeReplyRepository;

@Service
public class FreeReplyServiceImpl implements FreeReplyService {

	@Autowired
	private FreeReplyRepository replyRepository;
	
	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Transactional
	@Override
	public int register(FreeReplyVO vo) {
		boardRepository.updatedReplyCnt(vo.getBno(), 1);
		return replyRepository.insert(vo);
	}

	@Override
	public FreeReplyVO get(Long rno) {
		return replyRepository.read(rno);
	}

	@Override
	public int modify(FreeReplyVO vo) {
		return replyRepository.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		FreeReplyVO vo = replyRepository.read(rno);
		boardRepository.updatedReplyCnt(vo.getBno(), -1);
		return replyRepository.delete(rno);
	}

	@Override
	public FreeReplyPageDTO getList(Criteria criteria, Long bno) {
		return new FreeReplyPageDTO(
				replyRepository.getReplyCount(bno), 
				replyRepository.getList(bno, criteria));
	}

}
