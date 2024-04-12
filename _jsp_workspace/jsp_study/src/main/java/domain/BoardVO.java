package domain;

public class BoardVO {
	
	private int bno;
	private String title;
	private String writer;
	private String regdate;
	private String moddate;
	private String content;
	
	public BoardVO() {}

	//insert: title, writer, content
	public BoardVO(String title, String writer, String content) {
		this.title  = title;
		this.writer = writer;
		this.content = content;
	}
	//list : bno, title, writer, regdate
	public BoardVO(int bno, String title, String writer, String regdate) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
	}
	//update : bno, title, content
	public BoardVO(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
	//detail : all
	public BoardVO(int bno, String title, String writer, String content, String regdate, String moddate){
		this(bno, title, writer, regdate);
		this.moddate = moddate;
		this.content = content;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModdate() {
		return moddate;
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", regdate=" + regdate + ", moddate="
				+ moddate + ", content=" + content + "]";
	}
	
}
