package com.ezen.www.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private long fileSize;
	private int fileType;
	private int bno;
	private String regDate;
	

}
