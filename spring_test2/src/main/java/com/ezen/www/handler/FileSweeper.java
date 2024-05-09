package com.ezen.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ezen.www.domain.FileVO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Component
@Slf4j
public class FileSweeper {
	//직접 DB 접속을 해서 처리
	
	@Autowired
	private FileDAO fdao;
	
	private final String BASE_PATH = "/Users/oh-haeun/Desktop/_myProject/_java/_fileUpload/";
	
	//매일 정해진 시간에 스케줄러를 실행 => 매일 경로에 저장된 파일과 DB의 파일을 비교하고 DB에 없는 파일이면 경로에서도 삭제
	//cron = 주기 설정 "초 분 시 일 월 요일 년도(생략 가능)"
	@Scheduled(cron="0 19 13 * * *")
	public void fileSweeper() {
		log.info(">>>> FileSweeper Running start! : {}", LocalDateTime.now());
		//DB에 등록된 파일 가져오기
		List<FileVO> dbList = fdao.selectListAllFile();
		
		//저장소를 검색할 때 필요한 파일 경로 리스트(실제 존재해야 하는 리스트)
		List<String> currFiles = new ArrayList<String>();
		
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir()+File.separator+fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH+filePath+"_"+fileName);
			
			if(fvo.getFileType() > 0) {
				currFiles.add(BASE_PATH+filePath+"_th_"+fileName);
			}
		}
		
		log.info(">>>> currFile >> {}", currFiles);
		
		//오늘 날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", "//");
		
		//경로를 기반으로 저장되어 있는 파일을 검색
		File dir = Paths.get(BASE_PATH+today).toFile();
		File[] allFileObj = dir.listFiles();
		
		//실제 저장되어 있는 파일과 DB에 존재하는 파일을 비교하여 없는 파일을 삭제 진행
		for(File file : allFileObj) {
			String storedFileName = file.toPath().toString();
			//없으면 삭제 !! ! ! 
			if(!currFiles.contains(storedFileName)) {
				file.delete();
				log.info(">>>> delete file List >> {}", storedFileName);
			}
		}
		
		log.info(">>>> FileSweeper Running End! : {}", LocalDateTime.now());
	}
}
