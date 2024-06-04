package com.jobposting.service.fileupload;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileUploadService implements IFileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
    private final AmazonS3 amazonS3;
    private final String bucketName;
    private final long maxSize;

    public FileUploadService(
            @Value("${aws.accessKey}") String accessKey,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region,
            @Value("${aws.s3.bucketName}") String bucketName,
            @Value("${file.maxSize}") long maxSize) {

        if (accessKey == null || secretKey == null || region == null) {
            throw new IllegalArgumentException("AWS credentials and region must be provided");
        }

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        this.bucketName = bucketName;
        this.maxSize = maxSize;
    }

    @Override
    public String uploadFile(MultipartFile file,String fileName) throws Exception {
        logger.info("Uploading file: {}", file.getOriginalFilename());

        if (file.isEmpty()) {
            logger.error("Empty file uploaded");
            throw new IllegalArgumentException("Please upload a file");
        }

        if (file.getSize() > maxSize) {
            logger.error("File size exceeds the limit: {}", file.getSize());
            throw new IllegalArgumentException("File size exceeds the limit");
        }

        if (!"application/pdf".equalsIgnoreCase(file.getContentType())) {
            logger.error("Invalid file type: {}", file.getContentType());
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        File convertedFile = convertMultiPartToFile(file);
        try {
            String originalFileName = file.getOriginalFilename();
            String newFileName = renameFile(originalFileName,fileName);
            return uploadFileToS3(newFileName, convertedFile);
        } finally {
            if (convertedFile != null && convertedFile.exists()) {
                convertedFile.delete();
            }
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    private String uploadFileToS3(String fileName, File file) {
        amazonS3.putObject(bucketName, fileName, file);
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    private String renameFile(String originalFileName,String fileName) {
        String extension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            extension = originalFileName.substring(i + 1);
        }
        return fileName + "." + extension;
    }
}
