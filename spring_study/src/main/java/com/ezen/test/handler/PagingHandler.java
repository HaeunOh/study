package com.ezen.test.handler;

import com.ezen.test.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
	//list 화면 하단의 페이지 조적부 기능을 담당하는 변수들을 만들기
	//시작 페이지 번호, 끝 페이지 번호 (한페이지에서 보여지는 페이지네이션)
	//(진짜 마지막)끝 페이지 번호, 이전 / 다음 버튼 생성 여부
	//전체 게시글 수, 현재 페이지 번호, 한 페이지에 들어갈 게시글 수 (멤버변수로 받기)
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int realEndPage;
	
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)10)*10;
		this.startPage = endPage - 9;
		
		//진짜 끝 페이지
		this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
		
	}
}
