package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	// DB 설정 mybatis lib 사용하여 DB 구성 (orm,mapper 설정 후 여기서 DB-dao 연결하기)
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	} 

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert dao in!");
		//실제 DB로 저장하는 구문
		//sql.insert(mapperNameSpace.id, bvo);
		int isOk = sql.insert("BoardMapper.add", bvo);
		
		//insert, update, delete는 DB가 변경되는 구문 반드시 commit 필요
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info("selectoList dao in!");
		
		return sql.selectList("BoardMapper.list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("selectOne dao in!");
		return sql.selectOne("BoardMapper.detail", bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("update dao in!");
		int isOk = sql.update("BoardMapper.update", bvo);
		
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("delete dao in!");
		int isOk = sql.delete("BoardMapper.delete", bno);
		
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}