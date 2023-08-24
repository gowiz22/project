package com.petti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petti.domain.free_board.FreeBoardAttachVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
@RestController
@RequestMapping("/free/files")
public class FileUploadController {
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/upload")
	public ResponseEntity<List<FreeBoardAttachVO>> upload(@RequestParam("uploadFile") MultipartFile[] multipartFiles) {
		List<FreeBoardAttachVO> list = new ArrayList<FreeBoardAttachVO>(); 
		File uploadPath = new File("C:/storage", getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); 
		}
		for(MultipartFile multipartFile : multipartFiles) {
			FreeBoardAttachVO attachVO = new FreeBoardAttachVO();  

			String filName = multipartFile.getOriginalFilename(); // 파일이름
			String uuid = UUID.randomUUID().toString();
			File saveFile = new File(uploadPath,uuid + "_" + filName);
			
			attachVO.setFileName(filName); 
			attachVO.setUuid(uuid);
			attachVO.setUploadPath(getFolder());
			
			try {
				if(checkImageType(saveFile)) { // 썸네일 이미지 업로드
					attachVO.setFileType(true);
					FileOutputStream tumbnail = new FileOutputStream(new File(uploadPath,"s_"+uuid+"_"+filName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), tumbnail,50,50);
				}
				multipartFile.transferTo(saveFile); // 파일 저장
				list.add(attachVO);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}

	// 이미지 파일 체크 여부
	private boolean checkImageType(File file) throws IOException {
		String contentType = Files.probeContentType(file.toPath());
		return contentType!=null ? contentType.startsWith("image") : false;
	}

	// 날짜별 디렉토리 생성 
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(new Date());  
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("C:/storage/"+fileName);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> result = null; 
		
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(
					FileCopyUtils.copyToByteArray(file), 
					headers, 
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result; 
	}	
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(FreeBoardAttachVO vo){
		File file = new File("C:/storage/"+vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName());
		log.info(file);
		file.delete();
		if(vo.isFileType()) {
			file = new File("C:/storage/"+vo.getUploadPath(),"s_"+vo.getUuid() + "_" + vo.getFileName());
			file.delete();
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
}