package com.petti.service.free_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.repository.free_board.FreeBoardAttachRepository;
import com.petti.repository.free_board.FreeBoardRepository;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Autowired
	private FreeBoardAttachRepository attachRepository;
	
	@Override
	public List<FreeBoardVO> getList(Criteria criteria) {
		return boardRepository.getList(criteria);
	}

	@Transactional
	@Override
	public void register(FreeBoardVO vo) {
		boardRepository.insertSelectKey(vo);
		// 첨부파일이 있을 때 ...
		if(vo.getAttachList()!=null && !vo.getAttachList().isEmpty()) { 
			vo.getAttachList().forEach(attachFile->{
				attachFile.setBno(vo.getBno());
				attachRepository.insert(attachFile);
			});
		}
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

	@Override
	public List<CategoryVO> category() {
		return boardRepository.category();
	}

}
