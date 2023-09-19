package com.petti.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnounceVO;
import com.petti.repository.board.AnnoBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnoBoardServiceImpl implements AnnoBoardService {

	private final AnnoBoardRepository boardRepository;

	@Override
	public List<AnnounceVO> getList(Criteria criteria) {
		return boardRepository.getList(criteria);
	}

	@Override
	public void register(AnnounceVO board) {
		boardRepository.insertSelectKey(board);
	}
	
	@Override
	public AnnounceVO get(Long bno) {
		return boardRepository.read(bno);
	}

	@Override
	public boolean modify(AnnounceVO board) {
		return boardRepository.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		return boardRepository.delete(bno)==1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardRepository.getTotalCount(criteria);
	}


}
