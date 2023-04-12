package service_test;

import exeptions.DataBaseException;
import models.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.ImageRepoImpl;
import servises.impl.ImageServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ImageServiceTest {
    @Mock
    private ImageRepoImpl mockImageRepoImpl;

    private ImageServiceImpl imageService;


    @BeforeEach
    public void setUp() {
        mockImageRepoImpl = Mockito.mock(ImageRepoImpl.class);
        imageService = new ImageServiceImpl(mockImageRepoImpl);
    }

    @Test
    public void addImageTest() throws DataBaseException {
        Mockito.when(mockImageRepoImpl.addImage(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(12);
        Assertions.assertEquals(12, imageService.addImage("publicID", "URL", 600, 600));
    }

    @Test
    public void deleteImageTest() throws DataBaseException {
        Mockito.when(mockImageRepoImpl.deleteImage(Mockito.anyString())).thenReturn(13);
        Assertions.assertEquals(13, imageService.deleteImage("publicId"));
    }

    @Test
    public void getAllTest() throws DataBaseException {
        List<Image> imageList = new ArrayList<>();
        Mockito.when(mockImageRepoImpl.getAll()).thenReturn(imageList);
        Assertions.assertEquals(imageList, imageService.getAll());
    }
}
