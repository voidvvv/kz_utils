package com.kz.web.blog;

import com.kz.web.Util;
import com.kz.web.dto.KzBlogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component("localFileRepository")
@Slf4j
public class LocalFileRepository implements FileRepository{
    @Value("${kz.file.local.path:}")
    private String filePath;

    @Override
    public BlogFile saveBlogFile(KzBlogDTO kzBlobDTO) {
        String content = kzBlobDTO.getContent();
        String fileFormat = kzBlobDTO.getFileFormat();
        String fileName = compositeFileName(kzBlobDTO, fileFormat);
        String pathPrefix = fetchPathPrefix(kzBlobDTO);

        String filePath = pathPrefix + fileName;
        String fileMethod = "LOCAL";
        String fileUrl = pathPrefix + fileName;
        saveFile(fileUrl, content);
        BlogFile bFile = new BlogFile();
        bFile.setFileName(fileName);
        bFile.setFilePath(filePath);
        bFile.setFileMethod(fileMethod);
        bFile.setFileFormat(fileFormat);
        return bFile;
    }

    private void saveFile(String fileUrl, String content) {
        log.info("save file to {}", fileUrl);
        try {
            Files.write(Path.of(fileUrl), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("save file error, fileUrl: {}", fileUrl, e);
            throw new RuntimeException(e);
        }
    }

    private String fetchPathPrefix(KzBlogDTO kzBlobDTO) {
        return filePath.endsWith("\\") ? filePath : filePath + "\\";
    }

    private String compositeFileName(KzBlogDTO blog, String fileFormat) {
        try {
            Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
            String currentUser = null;
            if (authentication != null && authentication.getPrincipal() instanceof String) {
                currentUser = (String) authentication.getPrincipal();
            } else if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails) {
                currentUser = ((org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal()).getUsername();
            }
            MessageDigest SHA_1 = MessageDigest.getInstance("SHA-1");
            byte[] contentBytes = blog.getContent().getBytes();
            SHA_1.update(contentBytes);
            byte[] digest = SHA_1.digest();
            String hexFile = Util.byteArrToHexStr(digest);
//        byte[] titleBytes = blog.getTitle().getBytes(StandardCharsets.UTF_8);
//        String hexStr = new String(Hex.encode(titleBytes));
            String fileName = hexFile + "_" + currentUser + "_" + System.currentTimeMillis() + "." + fileFormat;
            return fileName;
        } catch (NoSuchAlgorithmException e) {
            log.error("composite file name error, blog: {}", blog, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyBlogFile(String path) {

    }
}
