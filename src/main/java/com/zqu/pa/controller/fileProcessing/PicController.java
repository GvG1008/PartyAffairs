package com.zqu.pa.controller.fileProcessing;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.common.exception.SystemErrorException;
import com.zqu.pa.service.picture.IFileUploadService;

@Controller
@RequestMapping("/pic")
public class PicController {
	
	@Autowired
    private IFileUploadService fileUploadService;
	
	@RequestMapping("/upload")
	@ResponseBody
	 public ServerResponse coverUpload(@RequestParam("file") MultipartFile multipartFile) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
            String fileName = fileUploadService.uploadPic(multipartFile);
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
     * 读取服务器中的封面图片
     * url例如：
     * http://localhost:8080/PartyAffairs/pic/item?imagePath=/1/d/1d3b03e5237f4c7e94cafb2c9be4ba01.jpg
     * @param imagePath
     * @param response
     * @throws IOException
     * 
     * 如果图片不存在，则返回默认图片，
     * 需在FileUloadServiceImpl配置DefaultImgPath物理默认图片路径
     */
    @RequestMapping("/item")
    public void loadImage(String imagePath,  HttpServletResponse response) throws IOException {

        FileInputStream fis = fileUploadService.getImageFile(imagePath);

        response.setContentType("image/jpg"); // 设置返回的文件类型
        response.setHeader("Access-Control-Allow-Origin", "*");// 设置该图片允许跨域访问
        IOUtils.copy(fis, response.getOutputStream());
    }
	
}
