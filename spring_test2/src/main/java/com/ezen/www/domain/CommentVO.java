package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
	private String writer;
	private String content;
	private String regDate;
	private int bno;
	private int cno;
	
}
