package com.school.student.controller;

import java.io.IOException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.student.entity.Document;
import com.school.student.repository.DocumentRepository;
import com.school.student.utils.FileStorageService;


@RestController
public class DocumentController {
	
		@Autowired
		private FileStorageService fileStorageService;
		
		@Autowired
		private DocumentRepository docrepo;
	
		@PostMapping("/document/add")
		@ResponseStatus(code = HttpStatus.CREATED)
		public Document addDocument(@RequestBody Document document) {
			 return null;
		}
	

	    @PostMapping("/document/upload")
	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartfile,@RequestParam int studId) throws IOException {
	    	System.out.println("In upload method   "+multipartfile.getName()+" studId :  "+studId);
	        // Save the file
	        String fileName = fileStorageService.storeFile(multipartfile);
	        Document doc = new Document();
	        
	        doc.setStudentId(studId);
    		doc.setFilename(fileName);
			doc.setDescription(multipartfile.getOriginalFilename());
			//doc.setDatetime("");
	 
	        docrepo.save(doc);
	        return ResponseEntity.ok("File " + fileName + " uploaded successfully");
	    }

}
