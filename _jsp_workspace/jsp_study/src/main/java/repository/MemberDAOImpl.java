package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	
	//log 객체 생성
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
			
	//sqlSession 객체 생성
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("mdao insert ok!");
		int isOk = sql.insert("MemberMapper.add", mvo);
				
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public MemberVO getID(MemberVO mvo) {
		log.info("mdao login ok!");
		return sql.selectOne("MemberMapper.login", mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("mdao lastLogin ok!");
		int isOk = sql.update("MemberMapper.last", id);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<MemberVO> selectAll() {
		log.info("mdao selectAll ok!");
		return sql.selectList("MemberMapper.all");
	}

	@Override
	public MemberVO getDetail(String id) {
		log.info("mdao detail ok!");
		return sql.selectOne("MemberMapper.one", id);
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("mdao update ok!");
		int isOk = sql.update("MemberMapper.edit", mvo);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info("mdao del ok!");
		int isOk = sql.delete("MemberMapper.remove", id);
		
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
	
	//namespace="MemberMapper" MemberMapper.id

}