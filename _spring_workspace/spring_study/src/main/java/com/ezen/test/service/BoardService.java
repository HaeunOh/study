package com.ezen.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardVO;

@Service
public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	void update(BoardVO bvo);

	void remove(BoardVO bvo);
	
}
