package com.example.SpringUploadDownloadMultipartFileMethod;

import com.example.SpringUploadDownloadMultipartFileMethod.service.ExcelFilesUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SpringUploadDownloadMultipartFileMethodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUploadDownloadMultipartFileMethodApplication.class, args);
	}

	@Bean("ExcelHeaderTemplate")
	public List<String> getHeaderTemplateOfExcel() throws IOException {
		try(FileInputStream excelFileInputStream = ExcelFilesUtil.findAExcelFile("ExcelTemplate")){
			XSSFSheet sheet = ExcelFilesUtil.getSheetOfAnExcelFile(excelFileInputStream, 0);
			return ExcelFilesUtil.getARowOfASheet(sheet, 0);
		}
	}

}
