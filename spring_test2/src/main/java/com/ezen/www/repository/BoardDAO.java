package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO boardVO);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	void modify(BoardVO bvo);

	void remove(BoardVO bvo);

	int getTotal(PagingVO pgvo);

	int cmtCnt();

	int selectOneBno();


}
