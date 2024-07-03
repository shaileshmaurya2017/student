package com.school.student.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileStorageService {
	
	 private final String uploadDir = "uploads";

	    public String storeFile(MultipartFile file) throws IOException{
	    	
	    	
	        // Generate a unique file name
	        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	        try {
	        	Path uploadPath = Paths.get(uploadDir);
	        	if(!Files.exists(uploadPath)) {
	        		Files.createDirectories(uploadPath);
	        		}
	        			    	
	            // Save the file to the designated location
	            Path filePath = Paths.get(uploadDir).resolve(fileName);
	            Files.copy(file.getInputStream(), filePath);
	            return fileName;
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to store file " + fileName, e);
	        }
	    }

}
