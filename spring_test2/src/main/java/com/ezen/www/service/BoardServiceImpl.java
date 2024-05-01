package com.ezen.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired(required=true)
	private BoardDAO bdao;

	@Override
	public void insert(BoardVO bvo) {
		log.info("board insert service in");
		bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(BoardVO bvo) {
		log.info("board getlist service in");
		return bdao.getList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("board getdetail service in");
		return bdao.getDetail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		log.info("board modify service in");
		bdao.modify(bvo);
	}

	@Override
	public void remove(BoardVO bvo) {
		log.info("board remove service in");
		bdao.remove(bvo);
	}
}
