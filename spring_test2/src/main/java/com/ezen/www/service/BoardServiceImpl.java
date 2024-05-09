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
	
	@Transactional
	@Override
	public BoardDTO getDetail(int bno) {
		bdao.plusViewCount(bno);
		log.info("board getdetail service in");
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		return bdto;
	}

//	@Override
//	public void modify(BoardVO bvo) {
//		log.info("board modify service in");
//		bdao.modify(bvo);
//	}

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
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.removeFile(uuid);
	}

	@Transactional
	@Override
	public int update(BoardDTO bdto) {
		log.info(">>> board update service ok");
		//파일 추가 작업
		//bvo => boardMapper / flist => fileMapper
		int isOk = bdao.update(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		//bvo업데이트 완료, 파일도 있다면 파일 추가
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public int cmtCnt() {
		// TODO Auto-generated method stub
		return bdao.cmtCnt();
	}
	
	@Override
	public int fileCnt() {
		// TODO Auto-generated method stub
		return bdao.fileCnt();
	}


}
