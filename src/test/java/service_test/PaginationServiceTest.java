package service_test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.TestRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaginationServiceTest {
    @Mock
    TestRepo mockTestRepo;

    PaginationService paginationService;

    @BeforeEach
    public void setUp() {
        mockTestRepo = Mockito.mock(TestRepo.class);
        paginationService = new PaginationService(mockTestRepo);
    }

//    @Test
//    public void nextPageForAll() throws DataBaseException {
//        List<models.Test> allTests = new ArrayList<>();
//        Mockito.when(mockTestRepo.nextPage(
//                Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(allTests);
//        assertEquals(allTests, paginationService.nextPage(
//                Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()));
//    }

//    @Test
//    public void nextPageForSomeSubject() throws DataBaseException {
//        List<models.Test> testForSubject = new ArrayList<>();
//        Mockito.when(mockTestRepo.nextPage(Mockito.anyString(),
//                Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(testForSubject);
//        assertEquals(testForSubject, paginationService.nextPage(Mockito.anyString(),
//                Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()));
//    }

}
