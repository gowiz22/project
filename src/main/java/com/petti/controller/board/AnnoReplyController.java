package com.petti.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petti.domain.Criteria;
import com.petti.domain.board.AnnoReplyVO;
import com.petti.service.board.AnnoReplyService;

@RestController
@RequestMapping("/annoReplies")
public class AnnoReplyController {

	@Autowired
	private AnnoReplyService replyService;
	
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody AnnoReplyVO vo) {
		int result = replyService.register(vo);
		return result ==1 ?
				new ResponseEntity<String>("sucess", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 특정게시물에 존재하는 댓글 리스트
		@GetMapping("/pages/{bno}/{page}")
		public ResponseEntity<List<AnnoReplyVO>> getList(
				@PathVariable Integer page, @PathVariable Long bno){
			Criteria criteria = new Criteria(page,10);
			return new ResponseEntity<>(replyService.getList(criteria, bno),HttpStatus.OK); 
		}
		
		@GetMapping("/{rno}")
		public ResponseEntity<AnnoReplyVO> get(@PathVariable Long rno){
			return new ResponseEntity<AnnoReplyVO>(replyService.get(rno),HttpStatus.OK);
		}
		
		@DeleteMapping("/{rno}")
		public ResponseEntity<String> remove(@PathVariable Long rno){
			int result = replyService.remove(rno);
			return result ==1 ? new ResponseEntity<String>("success",HttpStatus.OK) 
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
		public ResponseEntity<String> modify(
				@RequestBody AnnoReplyVO vo, @PathVariable Long rno){
			vo.setRno(rno);
			int result = replyService.modify(vo);
			return result == 1 ? new ResponseEntity<String>("success",HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
