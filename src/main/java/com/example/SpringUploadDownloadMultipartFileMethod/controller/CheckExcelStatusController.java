package com.example.SpringUploadDownloadMultipartFileMethod.controller;

import com.example.SpringUploadDownloadMultipartFileMethod.service.ExcelFilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Excel")
public class CheckExcelStatusController {
    @Autowired
    @Qualifier("ExcelHeaderTemplate")
    List<String> TemplateheaderList;
    @GetMapping("/checkExcel/{fileCode}")
    public boolean checkTheFile(@PathVariable("fileCode") String fileCode){
        return ExcelFilesUtil.checkTheExcelHeaders(TemplateheaderList, fileCode);
    }
}
