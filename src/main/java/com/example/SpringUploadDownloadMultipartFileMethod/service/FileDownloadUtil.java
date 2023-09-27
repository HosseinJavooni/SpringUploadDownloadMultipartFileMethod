package com.example.SpringUploadDownloadMultipartFileMethod.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDownloadUtil {
    public static Resource getFileAsResponse(String fileCode) throws IOException {
        Path uploadDirectory = Paths.get("src/Files-Uploaded");
        Stream<Path> pathStream = Files.list(uploadDirectory).filter(file ->
           file.getFileName().toString().startsWith(fileCode));
        Optional<Path> path = pathStream.findFirst();
        if (path.isPresent()){
            return new UrlResource(path.get().toUri());
        }
        return null;
    }
}

