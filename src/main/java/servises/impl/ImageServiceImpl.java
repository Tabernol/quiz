package servises.impl;

import dto.ImageDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Image;
import repo.ImageRepo;
import util.converter.ConvertToDtoAble;
import util.converter.ConvertToEntityAble;
import servises.ImageService;

import java.util.ArrayList;
import java.util.List;

/**
 * This class receives data from top-level classes.
 * it calls to ImageRepo.class for to do something actions
 */
@Slf4j
public class ImageServiceImpl implements ImageService,
        ConvertToEntityAble<Image, ImageDto>,
        ConvertToDtoAble<ImageDto, Image> {

    private final ImageRepo imageRepo;

    public ImageServiceImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    /**
     * this method calls to repo layer to insert new data in table 'image'
     *
     * @param imageDto contains information about loaded image:
     *                 publicId is a unique expression that returns "cloudinary" after inserting the image into the cloud
     *                 url      is a unique ГКД that returns "cloudinary" after inserting the image into the cloud
     *                 width    is width image
     *                 height   is height image
     * @return 1 if Image has inserted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int addImage(ImageDto imageDto) throws DataBaseException {
        log.info("SERVICE IMAGE add new image to database with publicId {}", imageDto);
        return imageRepo.addImage(mapToEntity(imageDto));
    }

    /**
     * This method return all image which stored in database
     *
     * @return List of Image
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<ImageDto> getAll() throws DataBaseException {
        log.info("SERVICE IMAGE get all images");
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (Image image : imageRepo.getAll()) {
            imageDtoList.add(mapToDto(image));
        }
        return imageDtoList;
    }

    /**
     * this method delete data about this image in database
     *
     * @param publicId is a unique expression that returns "cloudinary" after inserting the image into the cloud
     * @return 1 if Image has deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(String publicId) throws DataBaseException {
        log.info("SERVICE IMAGE delete from database with publicId " + publicId);
        return imageRepo.deleteImage(publicId);
    }

    @Override
    public List<String> canDeleteImage(String publicId) {
        return imageRepo.canDeleteImage(publicId);
    }

    @Override
    public Image mapToEntity(ImageDto imageDto) {
        return Image.builder()
                .publicId(imageDto.getPublicId())
                .url(imageDto.getUrl())
                .width(imageDto.getWidth())
                .height(imageDto.getHeight()).build();
    }

    @Override
    public ImageDto mapToDto(Image entity) {
        return ImageDto.builder()
                .publicId(entity.getPublicId())
                .url(entity.getUrl())
                .width(entity.getWidth())
                .height(entity.getHeight()).build();

    }
}
