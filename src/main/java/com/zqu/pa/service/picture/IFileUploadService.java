package com.zqu.pa.service.picture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

	String uploadPic(MultipartFile multipartFile);

	FileInputStream getImageFile(String imagePath) throws FileNotFoundException;

	String uploadFile(MultipartFile multipartFile);

	File getFile(String path)throws FileNotFoundException;

}
