package com.example.SpringUploadDownloadMultipartFileMethod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Component
public class FileUploadUtil {
    @Autowired
    @Qualifier("ExcelHeaderTemplate")
    List<String> TemplateheaderList;
    public void saveFile(String fileName, MultipartFile multipartFile) throws Exception{
        if(fileName.endsWith(".xlsx")){
            saveFileInCategory(fileName, "src/Files-Excel", multipartFile);
            if(!ExcelFilesUtil.checkTheExcelHeaders(TemplateheaderList, fileName))
                throw new Exception("Your Excel columns is not valid");
        } else {
            saveFileInCategory(fileName, "src/Files-Uploaded", multipartFile);
        }
    }
    public static void saveFileInCategory(String fileName, String path, MultipartFile multipartFile) throws IOException{
        Path uploadDirectory = Paths.get(path);
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Error saving upload file: " + fileName, e);
        }
    }
}
