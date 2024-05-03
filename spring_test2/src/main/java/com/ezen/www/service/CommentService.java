package com.ezen.www.service;


import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;

public interface CommentService {

	int insert(CommentVO cvo);

	PagingHandler getList(int bno, PagingVO pgvo);

	int modify(CommentVO cvo);

	int delete(int cno);

}
