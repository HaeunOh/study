package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardVO;

public interface BoardService {

	void insert(BoardVO bvo);

	List<BoardVO> getList(BoardVO bvo);

	BoardVO getDetail(int bno);

	void modify(BoardVO bvo);

	void remove(BoardVO bvo);

	

}
