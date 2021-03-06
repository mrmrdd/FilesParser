package com.ak.service;

import com.ak.entity.SourceFiles;
import com.ak.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This class uploads received from the client files into database.
 */
@Service
public class UploadService {
    private final FileRepository fileRepository;
    private final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    public UploadService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void save(MultipartFile[] files) {
        logger.info("Starting uploading files into database...");
        for (MultipartFile file : files) {
            try {
                String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                SourceFiles fileToUpload = new SourceFiles(name, bytes);
                fileRepository.save(fileToUpload);
                logger.info("File {} uploaded successfully.", name);
            } catch (IOException e) {
                logger.error("Error while file uploading: ", e);
                throw new RuntimeException(e);
            }
        }
    }
}