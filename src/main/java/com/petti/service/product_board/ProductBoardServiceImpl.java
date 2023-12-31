package com.petti.service.product_board;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeBoardAttachVO;
import com.petti.domain.product_board.ProductBoardAttachVO;
import com.petti.domain.product_board.ProductVO;
import com.petti.repository.product_board.ProductAttachRepository;
import com.petti.repository.product_board.ProductBoardRepository;
import com.petti.repository.product_board.ProductReplyRepository;

@Service
public class ProductBoardServiceImpl implements ProductBoardService {

	@Autowired
	private ProductBoardRepository boardRepository;
	
	@Autowired
	private ProductAttachRepository attachRepository;
	
	@Autowired
	private ProductReplyRepository replyRepository;
	
	@Override
	public List<ProductVO> getList(Criteria criteria) {
		return boardRepository.getList(criteria);
	}

	@Override
	public ProductVO get(Long pno) {
		return boardRepository.read(pno);
	}

	@Transactional
	@Override
	public void register(ProductVO vo) {
		boardRepository.insertSelectKey(vo);
			// 첨부파일 있을 때
		if(vo.getAttachList()!=null && !vo.getAttachList().isEmpty())
			vo.getAttachList().forEach(attachFile->{
				attachFile.setPno(vo.getPno());
				attachRepository.insert(attachFile);
			});
	}

	@Override
	public boolean modify(ProductVO vo) {
		List<ProductBoardAttachVO> attachList = vo.getAttachList();
		
		// 기존 파일 삭제
		if(attachList!=null) {
			List<ProductBoardAttachVO> delList = attachList.stream()
					.filter(attach -> attach.getPno()!=null).collect(Collectors.toList());
			deleteFilles(delList);
			delList.forEach(v->{
				attachRepository.delete(v.getUuid()); // DB 기록 삭제
			});
			
			// 새로운 파일 추가
			attachList.stream().filter(attach -> attach.getPno()==null).forEach(v->{
				v.setPno(vo.getPno());
				attachRepository.insert(v); // DB 저장
			});
		}
		
		return boardRepository.update(vo)==1;
	}

	@Transactional
	@Override
	public boolean remove(Long pno) {
		List<ProductBoardAttachVO> attachList = getAttachList(pno);
		if(attachList!=null) {
			deleteFilles(attachList);
			attachRepository.deleteAll(pno);
		}
		int replyCount = replyRepository.getReplyCount(pno);
		if(replyCount>0) {
			replyRepository.deletePno(pno);
		}

		return boardRepository.delete(pno)==1;
	}

	private void deleteFilles(List<ProductBoardAttachVO> delList) {
		delList.forEach(vo->{
			File file = new File("C:/storage/product/"+vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName());
			file.delete();
			if(vo.isFileType()) {
				file = new File("C:/storage/product/"+vo.getUploadPath(),"s_"+vo.getUuid() + "_" + vo.getFileName());
				file.delete();
			}
		});
	}	
	
	@Override
	public int totalCount(Criteria criteria) {
		return boardRepository.getTotalCount(criteria);
	}

	@Override
	public List<ProductBoardAttachVO> getAttachList(Long pno) {
		return attachRepository.selectByPno(pno);
	}

	@Override
	public ProductBoardAttachVO getAttach(String uuid) {
		return attachRepository.selectByUuid(uuid);
	}

	@Override
	public List<ProductBoardAttachVO> getThumbnail() {
		return attachRepository.selectThumbnail();
	}

}
