package com.petti.service.free_board;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.CategoryVO;
import com.petti.domain.free_board.FreeBoardAttachVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.domain.free_board.FreeLikeDTO;
import com.petti.repository.free_board.FreeBoardAttachRepository;
import com.petti.repository.free_board.FreeBoardLikeRepository;
import com.petti.repository.free_board.FreeBoardRepository;
import com.petti.repository.free_board.FreeReplyRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardRepository boardRepository;
	
	@Autowired
	private FreeBoardAttachRepository attachRepository;
	
	@Autowired
	private FreeBoardLikeRepository likeRepository;
	
	@Autowired
	private FreeReplyRepository replyRepository;
	
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
		
		List<FreeBoardAttachVO> attachList = vo.getAttachList();
		
		if(attachList!=null) {
			// 기존 파일 삭제
			List<FreeBoardAttachVO> delList = attachList.stream()
						.filter(attach -> attach.getBno()!=null)
						.collect(Collectors.toList());
			deleteFilles(delList);
			delList.forEach(a->{
				attachRepository.delete(a.getUuid()); // DB 기록 삭제
			});
			
			// 새로운 파일 추가
			attachList.stream().filter(attach -> attach.getBno()==null).forEach(a->{
				a.setBno(vo.getBno());
				attachRepository.insert(a); // DB 저장
			});
		}
		
		return boardRepository.update(vo)==1;
	}

	private void deleteFilles(List<FreeBoardAttachVO> delList) {
		delList.forEach(vo->{
			File file = new File("C:/storage/"+vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName());
			file.delete();
			if(vo.isFileType()) {
				file = new File("C:/storage/"+vo.getUploadPath(),"s_"+vo.getUuid() + "_" + vo.getFileName());
				file.delete();
			}
		});
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		List<FreeBoardAttachVO> attachList = getAttachList(bno);
		if(attachList!=null) {
			deleteFilles(attachList);
			attachRepository.deleteAll(bno);
		}
		int replyCount = replyRepository.getReplyCount(bno);
		if(replyCount>0) {
			replyRepository.deleteBno(bno);
		}
		
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

	// 게시물 추천
	@Transactional
	@Override
	public boolean hitLike(FreeLikeDTO likeDTO) {
		FreeLikeDTO result = likeRepository.get(likeDTO);
		if(result==null) { // 추천
			likeRepository.insert(likeDTO);
			boardRepository.updateLikeCnt(likeDTO.getBno(), 1);
			return true;
		} else { // 추천 취소
			likeRepository.delete(likeDTO);
			boardRepository.updateLikeCnt(likeDTO.getBno(), -1);
			return false;
		}
	}

	@Override
	public List<FreeBoardAttachVO> getAttachList(Long bno) {
		return attachRepository.selectByBno(bno);
	}

	@Override
	public FreeBoardAttachVO getAttach(String uuid) {
		return attachRepository.selectByUuid(uuid);
	}

}
