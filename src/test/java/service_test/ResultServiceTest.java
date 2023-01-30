package service_test;

import dto.ResultDto;
import exeptions.DataBaseException;
import models.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.ResultRepo;
import repo.TestRepo;
import servises.ResultService;
import servises.TestService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultServiceTest {
    @Mock
    ResultRepo mockResultRepo;

    ResultService resultService;

    @BeforeEach
    public void setUp() {
        mockResultRepo = Mockito.mock(ResultRepo.class);
        resultService = new ResultService(mockResultRepo);
    }
    @Test
    public void addResultTest() throws DataBaseException {
        Mockito.when(mockResultRepo.addResult(Mockito.anyLong(),
                Mockito.anyLong(), Mockito.anyInt())).thenReturn(1);
        assertEquals(1, resultService.addResult(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt()));
    }

//    @Test
//    public void getResultByUser() throws DataBaseException {
//        List<ResultDto> resultDtoList = new ArrayList<>();
//        Mockito.when(mockResultRepo.resultDtoList(Mockito.anyLong())).thenReturn(resultDtoList);
//        assertEquals(resultDtoList, resultService.getResultByUser(Mockito.anyLong()));
//
//    }

    @Test
    public void getGrade(){
        List<Boolean> result = List.of(true,false);
        assertEquals(50,resultService.getGrade(result,2));


    }

}
