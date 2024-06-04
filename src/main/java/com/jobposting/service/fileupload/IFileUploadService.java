package com.jobposting.service.fileupload;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {
    String uploadFile(MultipartFile file,String fileName) throws Exception;
}