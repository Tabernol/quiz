package service_test;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.TestRepo;
import servises.TestService;
import org.junit.jupiter.api.Test;
import servises.ValidatorService;
import validator.DataValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestServiceTest {
    @Mock
    TestRepo mockTestRepo;
    @Mock
    ValidatorService mockValidateService;
    TestService testService;

    @BeforeEach
    public void setUp() {
        mockValidateService = Mockito.mock(ValidatorService.class);
        mockTestRepo = Mockito.mock(TestRepo.class);
        testService = new TestService(mockTestRepo, mockValidateService);
    }

//    @Test
//    public void getAllTest() throws DataBaseException {
//        List<models.Test> allTests = new ArrayList<>();
//        Mockito.when(mockTestRepo.getAll()).thenReturn(allTests);
//        assertEquals(allTests, testService.getAll());
//    }

//    @Test
//    public void createTest() throws DataBaseException {
//        Mockito.when(mockTestRepo
//                .createTest(Mockito.anyString(), Mockito.anyString(),
//                        Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);
//        assertEquals(1, testService.createTest(Mockito.anyString(), Mockito.anyString(),
//                Mockito.anyInt(), Mockito.anyInt()));
//    }

    @Test
    public void getDistinctSubject() throws DataBaseException {
        List<String> subjects = new ArrayList<>();
        Mockito.when(mockTestRepo.getDistinctSubject()).thenReturn(subjects);
        assertEquals(subjects, testService.getDistinctSubjects());
    }

    @Test
    public void deleteTest() throws DataBaseException {
        Mockito.when(mockTestRepo.delete(Mockito.anyLong())).thenReturn(1);
        assertEquals(1, testService.delete(Mockito.anyLong()));
    }

    @Test
    public void getTest() throws DataBaseException {
        models.Test myTest = new models.Test();
        Mockito.when(mockTestRepo.get(Mockito.anyLong())).thenReturn(myTest);
        assertEquals(myTest, testService.get(Mockito.anyLong()));
    }

    @Test
    public void updateTest() throws DataBaseException, ValidateException {
        Mockito.when(mockTestRepo.updateInfoTest(Mockito.anyLong(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);
        assertEquals(1, testService.update(Mockito.anyLong(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()));
    }

    @Test
    public void AddPointPopularity() throws DataBaseException {
        Mockito.when(mockTestRepo.addPopularity(Mockito.anyLong())).thenReturn(1);
        assertEquals(1, testService.addPointPopularity(Mockito.anyLong()));
    }


    @Test
    public void getFilterTests() throws DataBaseException {
        List<models.Test> allTests = new ArrayList<>();
        List<models.Test> subjectTest = new ArrayList<>();
//        Mockito.when(mockTestRepo
//                .getAll( Mockito.anyString(), Mockito.anyInt())).thenReturn(allTests);//Must think about
//        Mockito.when(mockTestRepo
//                .getFilterTest(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(subjectTest);
//        assertEquals(allTests, testService.getFilterTests("all", Mockito.anyString(), Mockito.anyInt()));
//        assertEquals(subjectTest, testService.getFilterTests(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()));
    }

    @Test
    public void amountTests() throws DataBaseException {
        Mockito.when(mockTestRepo.getCount()).thenReturn(5);
        Mockito.when(mockTestRepo.getCount(Mockito.anyString())).thenReturn(3);
        assertEquals(1, testService.countPages("all", 5));
        assertEquals(3, testService.countPages("all", 2));
        assertEquals(1, testService.countPages("all", 10));
        assertEquals(1, testService.countPages(Mockito.anyString(), 3));
        assertEquals(3, testService.countPages(Mockito.anyString(), 1));
        assertEquals(2, testService.countPages(Mockito.anyString(), 2));

    }
}
