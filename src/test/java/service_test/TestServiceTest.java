package service_test;

import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.TestRepoImpl;
import servises.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import servises.impl.ValidatorServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestServiceTest {
    @Mock
    TestRepoImpl mockTestRepoImpl;
    @Mock
    ValidatorServiceImpl mockValidateService;
    TestServiceImpl testService;

    @BeforeEach
    public void setUp() {
        mockValidateService = Mockito.mock(ValidatorServiceImpl.class);
        mockTestRepoImpl = Mockito.mock(TestRepoImpl.class);
        testService = new TestServiceImpl(mockTestRepoImpl, mockValidateService);
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
        Mockito.when(mockTestRepoImpl.getDistinctSubject()).thenReturn(subjects);
        assertEquals(subjects, testService.getDistinctSubjects());
    }

    @Test
    public void deleteTest() throws DataBaseException {
        Mockito.when(mockTestRepoImpl.delete(Mockito.anyLong())).thenReturn(1);
        assertEquals(1, testService.delete(Mockito.anyLong()));
    }

    @Test
    public void getTest() throws DataBaseException {
        models.Test myTest = new models.Test();
        Mockito.when(mockTestRepoImpl.get(Mockito.anyLong())).thenReturn(myTest);
        assertEquals(myTest, testService.get(Mockito.anyLong()));
    }

    @Test
    public void updateTest() throws DataBaseException, ValidateException {
        Mockito.when(mockTestRepoImpl.update(Mockito.any(models.Test.class))).thenReturn(1);
        assertEquals(1, testService.update(new TestDto(12L,
                "newName", "newSubject", 45, 26)));
    }

    @Test
    public void AddPointPopularity() throws DataBaseException {
        Mockito.when(mockTestRepoImpl.addPopularity(Mockito.anyLong())).thenReturn(1);
        assertEquals(1, testService.addPointPopularity(Mockito.anyLong()));
    }


//    @Test
//    public void getFilterTests() throws DataBaseException {
//        List<models.Test> allTests = new ArrayList<>();
//        List<models.Test> subjectTest = new ArrayList<>();
//        Mockito.when(mockTestRepo
//                .getAll( Mockito.anyString(), Mockito.anyInt())).thenReturn(allTests);//Must think about
//        Mockito.when(mockTestRepo
//                .getFilterTest(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(subjectTest);
//        assertEquals(allTests, testService.getFilterTests("all", Mockito.anyString(), Mockito.anyInt()));
//        assertEquals(subjectTest, testService.getFilterTests(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()));
//    }

    @Test
    public void amountTests() throws DataBaseException {
        Mockito.when(mockTestRepoImpl.getCount()).thenReturn(5);
        Mockito.when(mockTestRepoImpl.getCount(Mockito.anyString())).thenReturn(3);
        assertEquals(1, testService.countPages("all", 5));
        assertEquals(3, testService.countPages("all", 2));
        assertEquals(1, testService.countPages("all", 10));
        assertEquals(1, testService.countPages(Mockito.anyString(), 3));
        assertEquals(3, testService.countPages(Mockito.anyString(), 1));
        assertEquals(2, testService.countPages(Mockito.anyString(), 2));
    }

    @Test
    public void getPageTestList() throws DataBaseException {
        List<models.Test> testList = new ArrayList<>();
        Mockito.when(mockTestRepoImpl.nextPage(Mockito.anyString())).thenReturn(testList);
        Assertions.assertEquals(testList,
                testService.getPageTestList("Sub", "order by ...", 2, 2, "admin"));
    }

    @Test
    public void changeStatusTest() throws DataBaseException {
        models.Test test = new models.Test();
        test.setStatus(models.Test.Status.BLOCKED);
        Mockito.when(mockTestRepoImpl.get(Mockito.anyLong())).thenReturn(test);
        Mockito.when(mockTestRepoImpl.changeStatus(Mockito.anyLong(),
                Mockito.any(models.Test.Status.class))).thenReturn(12);
        Assertions.assertEquals(12, testService.changeStatus(Mockito.anyLong()));
        test.setStatus(models.Test.Status.FREE);
        Assertions.assertEquals(12, testService.changeStatus(Mockito.anyLong()));
    }
}
