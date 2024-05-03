package com.ezen.www.handler;

import java.util.List;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;

	private int realEndPage;
	
	private List<CommentVO> cmtList;
	
	//생성자에서 모든값들이 계산되어 설정돼야 함
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)10) * 10;
		this.startPage = endPage - 9;
		
		this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		if(endPage > realEndPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEndPage;
		
	}
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
