package com.ezen.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.handler.PagingHandler;
import com.ezen.test.service.BoardService;

import lombok.extern.slf4j.Slf4j;



@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {

	@Autowired(required = true)
	private BoardService bsv;

	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}

	@PostMapping("/insert")
	public String insert(BoardVO bvo) {
		log.info(">>> bvo >>>> {}", bvo);
		int isOk = bsv.insert(bvo);
		return "redirect:/board/list";
	}
	
	/* 페이징 여기서 !! 
	 * pagingVO를 파라미터로 DB에 보내기 */
	@GetMapping("/list")
	public String list(Model m , PagingVO pgvo) {
		// 리턴 타입은 목적지 경로에 대한 타입 (destPage가 리턴)
		// Model 객체 => request.setAttribute 역할을 하는 객체
		int totalCount = bsv.getTotal(pgvo);
		log.info("totalCount >>>> "+totalCount+"개");
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		log.info(">>>> ph >>>> {}", ph);
		List<BoardVO> list = bsv.getList(pgvo);
		m.addAttribute("list", list);
		m.addAttribute("ph",ph);
		return "/board/list";
	}
	
	//getmapping으로 들어오는 경로와 나가는 경로가 동일하다면 void처리 가능 / 리턴 생략
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno")int bno) {
		log.info(">>> bno >>>> {}", bno);
		BoardVO bvo = bsv.getDetail(bno);
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("modify")
	public String modify(BoardVO bvo) {
		log.info(">>> modify bvo >>>> {}", bvo);
		bsv.update(bvo);
		//detail.jsp 로 가고 싶음 (수정된 페이지)
		//컨트롤러 내부에 있는 디테일 메서드 호출
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	@GetMapping("remove")
	public String remove(BoardVO bvo) {
		bsv.remove(bvo);
		return "redirect:/board/list?isDel="+bvo.getIsDel();
	}

	
	
	
	
	
	
	
	
	
	
}