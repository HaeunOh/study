package com.ezen.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardTest {
	
	@Inject
	public BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		log.info(">>>> test insert in >>>");
		for(int i=0; i<300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("test title"+i);
			bvo.setWriter("tester");
			bvo.setContent("test content"+i);
			
			bdao.insert(bvo);
		}
	}
}
