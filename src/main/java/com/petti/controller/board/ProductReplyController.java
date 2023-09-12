package com.petti.controller.board;

import java.util.List;

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
import com.petti.domain.product_board.ProductReplyVO;
import com.petti.domain.product_board.ReviewPageDTO;
import com.petti.service.product_board.ProductReplyService;

@RestController
@RequestMapping("/productReplies")
public class ProductReplyController {

	@Autowired
	private ProductReplyService replyService;

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ProductReplyVO vo) {
		int result = replyService.register(vo);
		return result ==1 ?
				new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 특정게시물에 존재하는 댓글 리스트
		@GetMapping("/pages/{page}/{pno}")
		public ResponseEntity<ReviewPageDTO> getList(
				@PathVariable Integer page, @PathVariable Long pno){
			Criteria criteria = new Criteria(page,10);
			return new ResponseEntity<>(replyService.getList(criteria, pno),HttpStatus.OK); 
		}
		@PostMapping("/pages/{pno}")
		public ResponseEntity<List<String>> getReviewerList(@PathVariable Long pno){
			return new ResponseEntity<>(replyService.getReviewerList(pno),HttpStatus.OK); 
		}

		@PreAuthorize("isAuthenticated()")
		@GetMapping("/{rno}")
		public ResponseEntity<ProductReplyVO> get(@PathVariable Long rno){
			return new ResponseEntity<ProductReplyVO>(replyService.get(rno),HttpStatus.OK);
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
				@RequestBody ProductReplyVO vo, @PathVariable Long rno){
			vo.setRno(rno);
			int result = replyService.modify(vo);
			return result == 1 ? new ResponseEntity<String>("success",HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
