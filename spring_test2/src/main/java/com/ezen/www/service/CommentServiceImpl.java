package com.ezen.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	
	@Autowired(required = true)
	private CommentDAO cdao;

	@Override
	public int insert(CommentVO cvo) {
		log.info("comment insert service in");
		return cdao.insert(cvo);
	}

	@Override
	public PagingHandler getList(int bno, PagingVO pgvo) {
		// cmtList 값을 ph 객체 안에 넣어주기
		List<CommentVO> list = cdao.getList(bno, pgvo);
		int totalCount = cdao.getSelectOneBnoTotalCount(bno);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.modify(cvo);
	}

	@Override
	public int delete(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}

	
}
