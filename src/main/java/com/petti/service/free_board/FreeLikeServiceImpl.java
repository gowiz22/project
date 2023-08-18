package com.petti.service.free_board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petti.repository.free_board.FreeBoardLikeRepository;

@Service
public class FreeLikeServiceImpl implements FreeLikeService {

	@Autowired
	private FreeBoardLikeRepository likeRepository;
	
	@Override
	public List<String> confimLike(Long bno) {
		return likeRepository.findLikeUser(bno);
	}

}
