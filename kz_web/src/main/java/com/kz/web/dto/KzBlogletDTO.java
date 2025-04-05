package com.kz.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KzBlogletDTO {
    private Integer id;
    private String title;
    private String description;
    private String fileFormat = "md";
    private String author;
    private List<String> tags;
    private List<String> categories;
}
