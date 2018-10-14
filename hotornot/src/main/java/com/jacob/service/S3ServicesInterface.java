package com.jacob.service;

import java.io.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

public interface S3ServicesInterface {
	public ByteArrayOutputStream downloadFile(String keyName);
	public void uploadFile(String keyName, MultipartFile file);
}
