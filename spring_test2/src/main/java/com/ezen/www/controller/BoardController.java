package com.ezen.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Controller
@Slf4j
public class BoardController {

	@Autowired(required=true)
	private BoardService bsv;
	
	@Autowired(required=true)
	private FileHandler fh;
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo, @RequestParam(name="files", required = false) MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			//파일이 있다면..
			flist = fh.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
		log.info(">>>>bvo >>{}", bvo);
		bsv.insert(bdto);
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		
		log.info(">>> pagingVO >>> {}", pgvo);
		
		//페이징 처리 추가
		List<BoardVO> list = bsv.getList(pgvo);
		
		//totalCount 구해오기
		int totalCount = bsv.getTotal(pgvo);
		
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		m.addAttribute("ph", ph);
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
	
	@GetMapping("/cmtCnt")
	public String cmtCnt() {
		int isOk = bsv.cmtCnt();
		if(isOk > 0) {
			log.info("board cmtCnt ok!");
			}
		
		return "redirect:/board/list";
	}

	
	
}
