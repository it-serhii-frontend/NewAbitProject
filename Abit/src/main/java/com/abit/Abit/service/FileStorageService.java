package com.abit.Abit.service;

import com.abit.Abit.entety.FileDB;
import com.abit.Abit.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    @Autowired
    private FileRepo fileRepo;

    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileRepo.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileRepo.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileRepo.findAll().stream();
    }
}