
package com.petti.controller.board;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.nio.file.AccessDeniedException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petti.domain.Criteria;
import com.petti.domain.Pagination;
import com.petti.domain.free_board.FreeBoardAttachVO;
import com.petti.domain.free_board.FreeBoardVO;
import com.petti.domain.free_board.FreeLikeDTO;
import com.petti.service.free_board.FreeBoardService;
import com.petti.service.free_board.FreeLikeService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/free")
@Log4j
public class FreeController {
	
	@Autowired
	private FreeBoardService boardService; 
	
	@Autowired
	private FreeLikeService likeService;
	
	@GetMapping("/list") 
	public String list(Model model, Criteria criteria) {
		model.addAttribute("list", boardService.getList(criteria));
		model.addAttribute("p",new Pagination(criteria, boardService.totalCount(criteria)));
		model.addAttribute("category", boardService.category());
		return "/board/free/free_list";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam(name="bno") Long bno, Model model, Criteria criteria, 
												HttpServletResponse response, HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		// 자정 시간 설정
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		
		long midnight = calendar.getTimeInMillis() +((24+9) * 60 * 60 * 1000) - now.getTime();
		log.info(midnight);
		
		int maxAgeInSeconds = (int)(midnight/1000);
		
		Cookie[] cookies = request.getCookies();
		boolean isNewVisit = true; // 방문 여부를 나타내는 플래그
		
		if(cookies != null) { // 쿠키가 존재할 경우
			for(Cookie cookie: cookies) {
				if(cookie.getName().contains("visit_cookie")) { // vist_cookie 존재 확인
					String cookieValue = cookie.getValue();
					String[] visitedBnos = cookieValue.split("_");
					
					boolean found = false; // "visit_cookie"에 조회한 bno의 존재 여부
					
					for(String visitedBnoStr : visitedBnos) {
						Long visitedBno = Long.valueOf(visitedBnoStr);
						if(visitedBno.equals(bno)) {
							found = true;
							break;
						}
					}
				
					if(!found) {
						if(cookieValue.length() >=4000) {
							cookie = new Cookie("visit_cookie", String.valueOf(bno));
							cookie.setMaxAge(maxAgeInSeconds);
							response.addCookie(cookie);
						} else {
							cookieValue = cookieValue + "_" + bno;
							cookie.setValue(cookieValue);
							cookie.setMaxAge(maxAgeInSeconds);
							response.addCookie(cookie);
						}
						boardService.countUp(bno);
					}
					isNewVisit = false;
					break; // 쿠키에 게시물의 bno가 존재하면 루프를 빠져나감
				}
			}
		} 
		
		if(isNewVisit) {
			// 쿠키가 없을 경우
			Cookie newCookie = new Cookie("visit_cookie",String.valueOf(bno));
	        newCookie.setMaxAge(maxAgeInSeconds);
	        response.addCookie(newCookie);
	        boardService.countUp(bno);
		}
		
		model.addAttribute("board", boardService.get(bno));
		model.addAttribute("category", boardService.category());
		model.addAttribute("likeUser", likeService.confirmLike(bno));
		return "/board/free/free_get";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("category", boardService.category());
		return "/board/free/free_register";
	}
	
	@PreAuthorize("isAuthenticated()")	
	@PostMapping("/register")
	public String register(FreeBoardVO vo, RedirectAttributes rttr) {
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/free/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public String modify(Long bno, Model model, Criteria criteria, Authentication auth) throws AccessDeniedException {
		FreeBoardVO vo = boardService.get(bno);
		String username = auth.getName();
		if(!vo.getWriter().equals(username) &&
				!auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new AccessDeniedException("Access denied");
		}
		model.addAttribute("board", boardService.get(bno));
		model.addAttribute("category", boardService.category());
		return "/board/free/free_modify";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify")
	public String modify(FreeBoardVO vo, RedirectAttributes rttr) {
		if(boardService.modify(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/free/list";
	}
	
	// 게시물 삭제 처리
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/free/list";
	}
	
	// 게시물 추천
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/like", produces = "plain/text; charset=utf-8")
	public ResponseEntity<String> hitLike(FreeLikeDTO likeDTO) {
		String message = likeDTO.getBno() +"번";
		if(boardService.hitLike(likeDTO)) {
			message += "게시글을 추천하였습니다.";
		} else {
			message += "게시글 추천을 취소하였습니다.";
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	// 첨부파일 리스트
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<FreeBoardAttachVO>> getAttachList(Long bno) {
		return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
	}
	
	@GetMapping("/getAttachFileInfo")
	@ResponseBody
	public ResponseEntity<FreeBoardAttachVO> getAttach(String uuid) {
		return new ResponseEntity<>(boardService.getAttach(uuid), HttpStatus.OK);
	}
}
