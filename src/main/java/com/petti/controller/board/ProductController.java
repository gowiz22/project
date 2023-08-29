package com.petti.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petti.domain.Criteria;
import com.petti.domain.Pagination;
import com.petti.domain.product_board.ProductBoardAttachVO;
import com.petti.domain.product_board.ProductVO;
import com.petti.repository.product_board.ProductReplyRepository;
import com.petti.service.product_board.ProductBoardService;
import com.petti.service.product_board.ProductReplyService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductBoardService boardService;
	
	@GetMapping("/list")
	public String list(Model model, Criteria criteria) {
		model.addAttribute("list", boardService.getList(criteria));
		model.addAttribute("p",  new Pagination(criteria, boardService.totalCount(criteria)));
		model.addAttribute("Thumbnail", boardService.getThumbnail());
		return "/board/product/product_list";
	}
	
	@GetMapping("/get")
	public String get(Long pno ,Model model, Criteria criteria) {
		model.addAttribute("product", boardService.get(pno));
		return "/board/product/product_get";
	}
	
	@GetMapping("/modify")
		public String modify(Long pno, Model model, Criteria criteria) {
		model.addAttribute("product", boardService.get(pno));
		return "/board/product/product_modify";
	}

	@GetMapping("/register")
	public String register() {
		return "/board/product/product_register";
	}	
	
	@PostMapping("/register")
	public String register(ProductVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getPno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/product/list";
	}

	@PostMapping("/modify")
	public String modify(ProductVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getPno());
			rttr.addFlashAttribute("operation", "modify");			
		}
		return "redirect:/product/list";
	}
	
	
	@PostMapping("/remove")
	public String remove(Long pno, RedirectAttributes rttr) {
		if(boardService.remove(pno)) {
			rttr.addFlashAttribute("result", pno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/product/list";
	}	
	
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<ProductBoardAttachVO>> getAttachList(Long pno) {
		return new ResponseEntity<List<ProductBoardAttachVO>>(boardService.getAttachList(pno),HttpStatus.OK);
	}

	@GetMapping("/getAttachFileInfo")
	@ResponseBody
	public ResponseEntity<ProductBoardAttachVO> getAttach(String uuid) {
		return new ResponseEntity<ProductBoardAttachVO>(boardService.getAttach(uuid),HttpStatus.OK);
	}
}
