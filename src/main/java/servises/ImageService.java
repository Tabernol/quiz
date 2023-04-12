package servises;

import exeptions.DataBaseException;
import models.Image;

import java.util.List;

public interface ImageService {
    int addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException;

    List<Image> getAll() throws DataBaseException;

    int deleteImage(String publicId) throws DataBaseException;

    List<String> canDeleteImage(String publicId);
}
