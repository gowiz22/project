package com.petti.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petti.domain.Criteria;
import com.petti.domain.free_board.FreeReplyPageDTO;
import com.petti.domain.free_board.FreeReplyVO;
import com.petti.service.free_board.FreeReplyService;

@RestController
@RequestMapping("/freeReplies")
public class FreeReplyController {

	@Autowired
	private FreeReplyService replyService;

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody FreeReplyVO vo) {
		int result = replyService.register(vo);
		return result ==1 ?
				new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 특정게시물에 존재하는 댓글 리스트
		@GetMapping("/pages/{page}/{bno}")
		public ResponseEntity<FreeReplyPageDTO> getList(
				@PathVariable Integer page, @PathVariable Long bno){
			Criteria criteria = new Criteria(page,10);
			return new ResponseEntity<>(replyService.getList(criteria, bno),HttpStatus.OK); 
		}

		@PreAuthorize("isAuthenticated()")
		@GetMapping("/{rno}")
		public ResponseEntity<FreeReplyVO> get(@PathVariable Long rno){
			return new ResponseEntity<FreeReplyVO>(replyService.get(rno),HttpStatus.OK);
		}
		
		@PreAuthorize("isAuthenticated()")
		@DeleteMapping("/{rno}")
		public ResponseEntity<String> remove(@PathVariable Long rno){
			int result = replyService.remove(rno);
			return result ==1 ? new ResponseEntity<String>("success",HttpStatus.OK) 
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
		public ResponseEntity<String> modify(
				@RequestBody FreeReplyVO vo, @PathVariable Long rno){
			vo.setRno(rno);
			int result = replyService.modify(vo);
			return result == 1 ? new ResponseEntity<String>("success",HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
