package me.cal1br.webserverprogramming.domain.image.service;

import me.cal1br.webserverprogramming.domain.image.model.PhotoUpload;

import java.io.InputStream;

public interface ImageService {
    /**
     * @returns path to file
     */
    String saveImage(final PhotoUpload photoUpload);

    InputStream getImageInputStreamByLink(final String link);
}
