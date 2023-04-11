package service_test;

import dto.AnswerDto;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.ResultRepoImpl;
import servises.AnswerService;
import servises.ResultService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultServiceTest {
    @Mock
    ResultRepoImpl mockResultRepoImpl;
    @Mock
    AnswerService answerService;

    ResultService resultService;


    @BeforeEach
    public void setUp() {
        mockResultRepoImpl = Mockito.mock(ResultRepoImpl.class);
        answerService = Mockito.mock(AnswerService.class);
        resultService = new ResultService(mockResultRepoImpl);
    }

    @Test
    public void addResultTest() throws DataBaseException {
        Mockito.when(mockResultRepoImpl.addResult(Mockito.anyLong(),
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
    public void getGrade() {
        List<Boolean> result = List.of(true, false);
        assertEquals(50, resultService.getGrade(result, 2));
    }

    @Test
    public void getAllResultByUser() throws DataBaseException {
        List<ResultDto> resultDtoList = new ArrayList<>();
        Mockito.when(mockResultRepoImpl.getAllResult(Mockito.anyLong())).thenReturn(resultDtoList);
        Assertions.assertEquals(resultDtoList, resultService.getAllResultByUserId(Mockito.anyLong()));
    }

    @Test
    public void getResultByQuestionWithNull() throws DataBaseException {
        Assertions.assertEquals(true,
                resultService.getResultByQuestion(12L, null));
    }

    @Test
    public void getResultByQuestion() throws DataBaseException {
        String[] userAnswers = {"2", "off", "1", "on"};
        Set<AnswerDto> answers = new HashSet<>();
        AnswerDto answer1 = new AnswerDto();
        answer1.setId(1L);
        answer1.setResult(true);
        AnswerDto answer2 = new AnswerDto();
        answer2.setId(2L);
        answer2.setResult(false);
        answers.add(answer1);
        answers.add(answer2);
        Mockito.when(answerService.getAnswers(123L)).thenReturn(answers);
        Assertions.assertEquals(true,
                resultService.getResultByQuestion(123L, userAnswers));
    }
}
