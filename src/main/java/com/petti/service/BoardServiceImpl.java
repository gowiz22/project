package com.petti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petti.domain.board.AnnounceVO;
import com.petti.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;

	@Override
	public List<AnnounceVO> getList() {
		return boardRepository.getList();
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


}
