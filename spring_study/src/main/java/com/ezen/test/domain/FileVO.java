package com.ezen.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileVO {
	private String uuid;
	private String save_dir;
	private String file_name;
	private int file_type;
	private long file_size; //sql : big int
	private int bno;
	private String reg_date;
}
