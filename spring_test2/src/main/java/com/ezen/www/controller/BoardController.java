package com.ezen.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Controller
@Slf4j
public class BoardController {

	@Autowired(required=true)
	private BoardService bsv;
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo) {
		log.info(">>>>bvo >>{}", bvo);
		bsv.insert(bvo);
		return "index";
		
	}
	
	@GetMapping("/list")
	public String list(Model m) {
		BoardVO bvo = new BoardVO();
		List<BoardVO> list = bsv.getList(bvo);
		m.addAttribute("list", list);
		return "/board/list";
	}
	
	@GetMapping({"/detail" , "/modify"})
	public void detail(Model m, @RequestParam("bno")int bno) {
		BoardVO bvo = bsv.getDetail(bno);
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		bsv.modify(bvo);
		return "redirect:/board/detail?bno="+bvo.getBno();
		
	}
	
	@GetMapping("/remove")
	public String remove(BoardVO bvo) {
		bsv.remove(bvo);
		return "redirect:/board/list?isDel="+bvo.getIsDel();
	}

	
	
}
