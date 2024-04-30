package com.ezen.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.repository.BoardDAO;
import com.ezen.test.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
	
	@Autowired(required=true)
	private BoardDAO bdao;
	
	@Autowired
	private FileDAO fdao;

	@Override
	public int insert(BoardDTO bdto) {
		log.info(">>> board insert service ok");
		int isOk = bdao.insert(bdto.getBvo());
		
		//file 처리 => bno는 아직 없음
		if(bdto.getFlist() == null) {
			return isOk;
		}else {
			//파일 있다는 뜻
			if(isOk > 0 && bdto.getFlist().size() > 0) {
				//bno 아직 없음 / insert를 통해서 자동 생성되기 때문에 DB로부터 가져와야 함
				int bno = bdao.selectBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info(">>> board getList service ok");
		bdao.cmtCnt();
		bdao.fileCnt();
		return bdao.getList(pgvo);
	}

	@Override
	public BoardDTO getDetail(int bno) {
		log.info(">>> board getDetail service ok");
		bdao.updateReadCount(bno);
		
		BoardDTO bdto = new BoardDTO();
		BoardVO bvo = bdao.getDetail(bno); //기존에 처리된 bvo 객체
		bdto.setBvo(bvo);
		bdto.setFlist(fdao.getFileList(bno));
		return bdto;
	}

	@Override
	public void update(BoardDTO bdto) {
		log.info(">>> board update service ok");
		//파일 추가 작업
		//bvo => boardMapper / flist => fileMapper
		int isOk = bdao.update(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return;
		}
		
		//bvo업데이트 완료, 파일도 있다면 파일 추가
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		
	}

	@Override
	public void remove(BoardVO bvo) {
		log.info(">>> board remove service ok");
		bdao.remove(bvo);
		
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		return bdao.getTotal(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.removeFile(uuid);
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
