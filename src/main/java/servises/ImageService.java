package servises;

import exeptions.DataBaseException;
import models.Image;
import repo.ImageRepo;

import java.util.List;

public class ImageService {
    private ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public void addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException {
        imageRepo.addImage(publicId, url, width, height);
    }

    public List<Image> getAll() throws DataBaseException {
        return imageRepo.getAll();
    }

    public int deleteImage(String publicId) throws DataBaseException {
        return imageRepo.deleteImage(publicId);
    }
}
