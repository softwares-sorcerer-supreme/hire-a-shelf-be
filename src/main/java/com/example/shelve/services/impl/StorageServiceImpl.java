package com.example.shelve.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.shelve.firebase_config.FirebaseConfig;
import com.example.shelve.services.StorageService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
            FirebaseConfig firebaseConfig = new FirebaseConfig();
            File file = this.convertToFile(multipartFile, fileName);
            String TEMP_URL = firebaseConfig.uploadFile(file, fileName);
            file.delete();
            return TEMP_URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsuccessfully Uploaded!";
        }

    }
}
