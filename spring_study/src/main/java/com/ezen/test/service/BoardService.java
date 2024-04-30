package com.ezen.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;

@Service
public interface BoardService {

	int insert(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO getDetail(int bno);

	void update(BoardDTO bdto);

	void remove(BoardVO bvo);

	int getTotal(PagingVO pgvo);

	int removeFile(String uuid);

	int cmtCnt();

	int fileCnt();
	
}
