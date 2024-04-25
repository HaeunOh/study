package com.ezen.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
	
	@Autowired(required=true)
	private BoardDAO bdao;

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>> board insert service ok");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info(">>> board getList service ok");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>> board getDetail service ok");
		bdao.updateReadCount(bno);
		return bdao.getDetail(bno);
	}

	@Override
	public void update(BoardVO bvo) {
		log.info(">>> board update service ok");
		bdao.update(bvo);
	}

	@Override
	public void remove(BoardVO bvo) {
		log.info(">>> board remove service ok");
		bdao.remove(bvo);
		
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		return bdao.getTotal(pgvo);
	}
}