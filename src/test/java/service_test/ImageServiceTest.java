package service_test;

import exeptions.DataBaseException;
import models.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.AnswerRepo;
import repo.ImageRepo;
import servises.AnswerService;
import servises.ImageService;
import servises.ValidatorService;

import java.util.ArrayList;
import java.util.List;

public class ImageServiceTest {
    @Mock
    private ImageRepo mockImageRepo;

    private ImageService imageService;


    @BeforeEach
    public void setUp() {
        mockImageRepo = Mockito.mock(ImageRepo.class);
        imageService = new ImageService(mockImageRepo);
    }

    @Test
    public void addImageTest() throws DataBaseException {
        Mockito.when(mockImageRepo.addImage(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(12);
        Assertions.assertEquals(12, imageService.addImage("publicID", "URL", 600, 600));
    }

    @Test
    public void deleteImageTest() throws DataBaseException {
        Mockito.when(mockImageRepo.deleteImage(Mockito.anyString())).thenReturn(13);
        Assertions.assertEquals(13, imageService.deleteImage("publicId"));
    }

    @Test
    public void getAllTest() throws DataBaseException {
        List<Image> imageList = new ArrayList<>();
        Mockito.when(mockImageRepo.getAll()).thenReturn(imageList);
        Assertions.assertEquals(imageList, imageService.getAll());
    }
}
