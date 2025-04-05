package com.kz.web.blog;

import com.kz.web.dto.KzBlogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component("localFileRepository")
@Slf4j
public class LocalFileRepository implements FileRepository{
    @Override
    public BlogFile saveBlogFile(KzBlogDTO kzBlobDTO) {
        String content = kzBlobDTO.getContent();
        String fileFormat = kzBlobDTO.getFileFormat();
        String fileName = compositeFileName(kzBlobDTO.getTitle(), fileFormat);
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
    }

    private String fetchPathPrefix(KzBlogDTO kzBlobDTO) {
        return null;
    }

    private String compositeFileName(String title, String fileFormat) {
        byte[] bytes = title.getBytes(StandardCharsets.UTF_8);
        return null;
    }

    @Override
    public void modifyBlogFile(String path) {

    }
}
