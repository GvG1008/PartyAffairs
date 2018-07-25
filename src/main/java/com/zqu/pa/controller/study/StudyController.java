package com.zqu.pa.controller.study;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.zqu.pa.common.Const;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.study.IFileService;
import com.zqu.pa.utils.FTPSSMLoad;



@Controller
@RequestMapping("/study/")
public class StudyController {

    @Autowired
    private IFileService iFileService;
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file",required = false) MultipartFile file,HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file,path);
        String url = Const.FTP_PREFIX+targetFileName;
    
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }
    @RequestMapping("download.do")
    @ResponseBody
    public ServerResponse download(HttpServletResponse response) {
        System.out.println("下载文件");
        iFileService.download(response, null, "299744c7-c482-4c57-b744-586ceee7fca6.docx");
        return null;    
    }
    @RequestMapping("uploadtest.do")
    @ResponseBody
    public ServerResponse uploadtest(@RequestParam(value = "upload_file",required = false) MultipartFile file,HttpServletRequest request,String path){
        Map fileMap = FTPSSMLoad.upload(file, request,path);
        return ServerResponse.createBySuccess(fileMap);
    }
    @RequestMapping("downloadtest.do")
    @ResponseBody
    public ServerResponse downloadtest(HttpServletResponse response,String path,String filename) {
        System.out.println("下载文件"+path+filename);
        //http://localhost:8080/PartyAffairs/study/downloadtest.do?path=/vodeo/&filename=1.jpg
        FTPSSMLoad.download(response, path, filename);
        return null;    
    }
}
