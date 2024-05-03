package com.ezen.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired(required=true)
	private BoardDAO bdao;
	
	@Autowired(required=true)
	private FileDAO fdao;

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		//bvo 저장 후 bno set 한 후 fileVO 저장
		int isOk = bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			int bno = bdao.selectOneBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		
			return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("board getlist service in");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("board getdetail service in");
		return bdao.getDetail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		log.info("board modify service in");
		bdao.modify(bvo);
	}

	@Override
	public void remove(BoardVO bvo) {
		log.info("board remove service in");
		bdao.remove(bvo);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotal(pgvo);
	}

	@Override
	public int cmtCnt() {
		// TODO Auto-generated method stub
		return bdao.cmtCnt();
	}
}
