package com.example.SpringUploadDownloadMultipartFileMethod.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long size;
}
