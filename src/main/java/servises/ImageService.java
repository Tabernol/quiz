package servises;

import dto.ImageDto;
import exeptions.DataBaseException;
import models.Image;

import java.util.List;

public interface ImageService {
    int addImage(ImageDto imageDto) throws DataBaseException;

    List<ImageDto> getAll() throws DataBaseException;

    int delete(String publicId) throws DataBaseException;

    List<String> canDeleteImage(String publicId);
}
