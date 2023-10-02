package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void addFile(MultipartFile fileUpload, int userid) throws IOException {
        File file = File.builder()
                .contentType(fileUpload.getContentType())
                .fileData(fileUpload.getBytes())
                .filename(fileUpload.getOriginalFilename())
                .fileSize(Long.toString(fileUpload.getSize()))
                .userid(userid)
                .build();
        fileMapper.storeFile(file);
    }

    public List<File> getUploadedFiles(Integer userid) {
        return fileMapper.getAllFiles(userid);
    }

    public boolean isFileAvailable(String filename, Integer userid) {
        File file = fileMapper.getFile(userid, filename);

        if (file != null) {
            return false;
        }

        return true;
    }

    public int deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }

    public File getFileById(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }
}
