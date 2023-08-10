package com.petti.service.free_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.repository.free_board.FreeBoardRepository;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Override
	public List<FreeBoardVO> getList(Criteria criteria) {
		return boardRepository.getList(criteria);
	}

	@Override
	public void register(FreeBoardVO vo) {
		boardRepository.insert(vo);
	}

	@Override
	public FreeBoardVO get(Long bno) {
		return boardRepository.read(bno);
	}

	@Override
	public boolean modify(FreeBoardVO vo) {
		return boardRepository.update(vo)==1;
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
