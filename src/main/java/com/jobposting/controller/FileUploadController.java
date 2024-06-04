package com.jobposting.controller;

import com.jobposting.config.security.JwtService;
import com.jobposting.dto.user.UserResponseDto;
import com.jobposting.service.fileupload.FileUploadService;
import com.jobposting.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private final UserService userService;
    private final JwtService jwtService;
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestHeader("Authorization") String authorizationHeader
            , @RequestParam("file") MultipartFile file) {

        String token = authorizationHeader.substring(7);
        UserResponseDto userResponseDto = userService.getUser(jwtService.extractUsername(token));
        String fileName = String.valueOf(userResponseDto.getId()+"_").concat(userResponseDto.getFirstname());
        logger.info("Upload request received");

        try {
            String s3FileUrl = fileUploadService.uploadFile(file,fileName);
            return ResponseEntity.ok("File uploaded successfully. S3 URL: " + s3FileUrl);
        } catch (IllegalArgumentException e) {
            logger.error("File upload error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to upload file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}

