package com.kz.web.blog;

import com.kz.web.dto.KzBlogDTO;

public interface FileRepository {
    /**
     * 保存文件
     * @param kzBlobDTO
     * @return 实际文件的获取路径
     */
    public BlogFile saveBlogFile(KzBlogDTO kzBlobDTO);

    public void modifyBlogFile(String path);
}
