package com.petti.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petti.board.domain.BoardVO;
import com.petti.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	
	@Override
	public List<BoardVO> getList() {
		return boardRepository.getList();
	}

	@Override
	public void register(BoardVO board) {
		boardRepository.insert(board);
	}

	@Override
	public BoardVO get(Long bno) {
		return boardRepository.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		return boardRepository.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		return boardRepository.delete(bno)==1;
	}

}
