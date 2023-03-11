package servises;

import exeptions.DataBaseException;
import models.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.ImageRepo;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * it calls to ImageRepo.class for to do something actions
 */
public class ImageService {
    private static final Logger logger = LogManager.getLogger(ImageService.class);
    private ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo) {
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
     * @throws DataBaseException
     */
    public int addImage(String publicId, String url, Integer width, Integer height) throws DataBaseException {
        logger.info("SERVICE IMAGE add new image to database with publicId " + publicId);
        return imageRepo.addImage(publicId, url, width, height);
    }

    /**
     * This method return all image which stored in database
     *
     * @return List of Image
     * @throws DataBaseException
     */
    public List<Image> getAll() throws DataBaseException {
        logger.info("SERVICE IMAGE get all images");
        return imageRepo.getAll();
    }

    /**
     * this method delete data about this image in database
     *
     * @param publicId is a unique expression that returns "cloudinary" after inserting the image into the cloud
     * @return 1 if Image has deleted
     * @throws DataBaseException
     */
    public int deleteImage(String publicId) throws DataBaseException {
        logger.info("SERVICE IMAGE delete from database with publicId " + publicId);
        return imageRepo.deleteImage(publicId);
    }

    public List<String> canDeleteImage(String publicId){
        return imageRepo.canDeleteImage(publicId);
    }
}
