package com.zqu.pa.controller.fileProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.common.exception.SystemErrorException;
import com.zqu.pa.service.picture.IFileUploadService;

/**
 *文件上传
 * */
@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
    private IFileUploadService fileUploadService;
	
	@RequestMapping("/upload")
	@ResponseBody
	 public ServerResponse fileUpload(@RequestParam("file") MultipartFile multipartFile) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
            String fileName = fileUploadService.uploadFile(multipartFile);
            hashMap.put("path",fileName);
        } catch (SystemErrorException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("由于未知错误，操作失败");
        }
		return ServerResponse.createBySuccess(hashMap);
	}
	
	 /**
     * 读取服务器中的文件
     * url例如：
     * http://localhost:8080/PartyAffairs/file/download?path=/1/d/1d3b03e5237f4c7e94cafb2c9be4ba01.jpg
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String path,  HttpServletResponse response) throws IOException {
    	
    	HttpHeaders headers = new HttpHeaders();    
        File file = fileUploadService.getFile(path);
        
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置为文件名
        headers.setContentDispositionFormData("attachment", "partyOffairs");    
       
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                              headers, HttpStatus.CREATED); 
    }

}
