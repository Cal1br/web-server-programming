package me.cal1br.webserverprogramming.domain.image.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PhotoUpload {
    private String title;
    private MultipartFile file;
}