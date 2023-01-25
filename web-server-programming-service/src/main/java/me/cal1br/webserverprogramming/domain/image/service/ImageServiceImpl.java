package me.cal1br.webserverprogramming.domain.image.service;

import me.cal1br.webserverprogramming.domain.image.model.PhotoUpload;
import me.cal1br.webserverprogramming.exception.InvalidModelException;
import me.cal1br.webserverprogramming.exception.UnsupportedImageFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss_SSS");

    private final String imageLocationPrefix;
    private final String[] validPictureExtensions;

    @Autowired
    public ImageServiceImpl(
            @Value("${image.location:/images/}") final String fileLocation,
            @Value("${image.allowed_file_formats:.png,.jpg,.jpeg}") final String[] validPictureExtensions
    ) {
        this.imageLocationPrefix = fileLocation;
        this.validPictureExtensions = validPictureExtensions;
    }

    @Override
    public String saveImage(final PhotoUpload photoUpload) {
        final String contentType = photoUpload.getFile().getContentType();
        if (photoUpload.getFile() == null || photoUpload.getFile().isEmpty()) {
            throw new InvalidModelException("Photo can't be empty!");
        }
        if (contentType == null || !contentType.contains("/")) {
            throw new InvalidModelException("Photo must contain content type!");
        }
        if (validPictureExtensions != null && validPictureExtensions.length > 0) {
            final String name = photoUpload.getFile().getName();
            if (Arrays.stream(validPictureExtensions).noneMatch(name::endsWith)) {
                throw new UnsupportedImageFormatException("Image must be one of the following: " + String.join(", ", validPictureExtensions));
            }
        }
        final String imgSuffix = photoUpload.getTitle() + timeFormatter.format(Instant.now()) +
                contentType.substring(contentType.indexOf('/') + 1);
        final File file = new File(imageLocationPrefix + imgSuffix);

        try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(photoUpload.getFile().getBytes());
        } catch (IOException ioException) {
            LOGGER.error("An exception has occurred while processing: {} from user: ", imgSuffix);//todo
        }
        return imgSuffix;
    }

    @Override
    public InputStream getImageInputStreamByLink(final String suffix) {
        if (!StringUtils.hasText(suffix)) {
            LOGGER.warn("Requested image with an empty link!");
            throw new IllegalArgumentException("The provided link is empty!");
        }
        try {
            return new FileInputStream(new File(imageLocationPrefix + suffix));
        } catch (IOException exception) {
            LOGGER.error("Input output exception for image: {}", suffix);
        }
        throw new IllegalStateException("Couldn't find the image you were looking for!");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDirectoryPath() throws IOException {
        Files.createDirectories(Paths.get(this.imageLocationPrefix));
    }
}
