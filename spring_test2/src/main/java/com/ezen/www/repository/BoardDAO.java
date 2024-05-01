package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;

public interface BoardDAO {

	void insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	void modify(BoardVO bvo);

	void remove(BoardVO bvo);

}
