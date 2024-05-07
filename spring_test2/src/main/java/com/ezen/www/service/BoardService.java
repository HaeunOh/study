package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO getDetail(int bno);

//	void modify(BoardVO bvo);

	void remove(BoardVO bvo);

	int getTotal(PagingVO pgvo);

	int cmtCnt();

	int removeFile(String uuid);

	int update(BoardDTO bdto);

	

}
