package com.example.SpringUploadDownloadMultipartFileMethod.controller;

import com.example.SpringUploadDownloadMultipartFileMethod.model.FileUploadResponse;
import com.example.SpringUploadDownloadMultipartFileMethod.service.FileUploadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
        @RequestParam("file")MultipartFile multipartFile) throws IOException {
        String guid = UUID.randomUUID().toString();
        String fileName = guid + "_" + StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        FileUploadUtil.saveFile(fileName, multipartFile);
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/"+fileName);
        return ResponseEntity.ok(response);
    }
}
