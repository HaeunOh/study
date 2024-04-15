package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;


public class MemberServiceImpl implements MemberService {
	
	//log 객체 생성
		private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	private MemberDAO mdao;
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}
	@Override
	public int register(MemberVO mvo) {
		log.info("join service ok!");
		return mdao.insert(mvo);
	}
	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login service ok!");
		return mdao.getID(mvo);
	}
	@Override
	public int lastLogin(String id) {
		log.info("lastLogin service ok!");
		return mdao.lastLogin(id);
	}
	@Override
	public List<MemberVO> getList() {
		log.info("list service ok!");
		return mdao.selectAll();
	}
	@Override
	public MemberVO getDetail(String id) {
		log.info("detail service ok!");
		return mdao.getDetail(id);
	}
	@Override
	public int update(MemberVO mvo) {
		log.info("update service ok!");
		return mdao.update(mvo);
	}
	@Override
	public int delete(String id) {
		log.info("del service ok!");
		return mdao.delete(id);
	}
		


}
