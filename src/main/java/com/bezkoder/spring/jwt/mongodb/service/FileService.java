package com.bezkoder.spring.jwt.mongodb.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.dto.LoadFile;

public interface FileService {
	 String addFile(MultipartFile upload) throws IOException;
	 LoadFile downloadFile(String id) throws IOException;
}
