package com.ezen.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.CommentVO;
import com.ezen.test.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		log.info("comment post service in!");
		return cdao.post(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("comment getList service in!");
		return cdao.getList(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		return cdao.remove(cno);
	}
}