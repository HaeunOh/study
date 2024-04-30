package com.ezen.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.handler.FileHandler;
import com.ezen.test.handler.PagingHandler;
import com.ezen.test.service.BoardService;

import lombok.extern.slf4j.Slf4j;
@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {

	@Autowired(required = true)
	private BoardService bsv;
	
	@Autowired
	private FileHandler fhd;

	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	//@RequestParam("name")String name = 파라미터를 받을 때
	//required : 필수여부
	@PostMapping("/insert")
	public String insert(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> bvo >>>> {}", bvo);
		log.info(">>> files >>>> {}", files);
		
		//파일 핸들러 처리
		//파일 저장 처리 => fileList 리턴
		List <FileVO> flist = null;
		
		//파일이 있을 경우에만 핸들러 호출
		if(files[0].getSize() > 0) {
			//핸들러 호출
			flist = fhd.uploadFiles(files);
			log.info(">>> flist >>>> {}", flist);
		}
		
		BoardDTO bdto = new BoardDTO(bvo,flist);
		int isOk = bsv.insert(bdto);
		if(isOk > 0) {
		log.info("board insert ok!");
		}
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
		BoardDTO bdto = bsv.getDetail(bno);
		log.info(">>> bdto >>>{}", bdto);
		m.addAttribute("bdto", bdto);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, @RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info(">>> modify bvo >>>> {}", bvo);
		List<FileVO> flist = null;
		
		//FileHandler multipartfile[] => flist
		if(files[0].getSize() > 0) {
			flist = fhd.uploadFiles(files);
		}
		
		BoardDTO bdto = new BoardDTO(bvo, flist);
 		bsv.update(bdto);
		//detail.jsp 로 가고 싶음 (수정된 페이지)
		//컨트롤러 내부에 있는 디테일 메서드 호출
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	@GetMapping("/remove")
	public String remove(BoardVO bvo) {
		bsv.remove(bvo);
		return "redirect:/board/list?isDel="+bvo.getIsDel();
	}
	
	@DeleteMapping(value="/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid) {
		log.info(">>> uuid >>> {}", uuid);
		
		int isOk = bsv.removeFile(uuid);
		
		return isOk > 0? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//내가만든 메서드
	@GetMapping("/cmtCnt")
	public String cmtCnt() {
		int isOk = bsv.cmtCnt();
		if(isOk > 0) {
			log.info("board cmtCnt ok!");
			}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/fileCnt")
	public String fileCnt() {
		int isOk = bsv.fileCnt();
		if(isOk > 0) {
			log.info("board fileCnt ok!");
		}
		
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
}
