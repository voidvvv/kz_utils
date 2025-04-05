package com.kz.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KzBlogDTO {
    private Integer id;
    private String title;
    private String content;
    private String description;
    private String fileFormat = "md";
    private List<String> tags;
    private List<String> categories;
}
