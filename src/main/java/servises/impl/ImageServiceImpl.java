package servises.impl;

import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Image;
import repo.ImageRepo;
import repo.impl.ImageRepoImpl;
import servises.ImageService;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * it calls to ImageRepo.class for to do something actions
 */
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;

    public ImageServiceImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    /**
     * this method calls to repo layer to insert new data in table 'image'
     *
     * @param publicId is a unique expression that returns "cloudinary" after inserting the image into the cloud
     * @param url      is a unique ГКД that returns "cloudinary" after inserting the image into the cloud
     * @param width    is width image
     * @param height   is height image
     * @return 1 if Image has inserted
     * @throws DataBaseException is wrapper of SQLException
     */
    public int addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException {
        log.info("SERVICE IMAGE add new image to database with publicId " + publicId);
        return imageRepo.addImage(publicId, url, width, height);
    }

    /**
     * This method return all image which stored in database
     *
     * @return List of Image
     * @throws DataBaseException is wrapper of SQLException
     */
    public List<Image> getAll() throws DataBaseException {
        log.info("SERVICE IMAGE get all images");
        return imageRepo.getAll();
    }

    /**
     * this method delete data about this image in database
     *
     * @param publicId is a unique expression that returns "cloudinary" after inserting the image into the cloud
     * @return 1 if Image has deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    public int deleteImage(String publicId) throws DataBaseException {
        log.info("SERVICE IMAGE delete from database with publicId " + publicId);
        return imageRepo.deleteImage(publicId);
    }

    public List<String> canDeleteImage(String publicId){
        return imageRepo.canDeleteImage(publicId);
    }
}
