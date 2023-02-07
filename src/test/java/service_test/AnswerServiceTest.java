package service_test;

import exeptions.DataBaseException;
import exeptions.QuizException;
import exeptions.ValidateException;
import models.Answer;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import repo.AnswerRepo;
import servises.AnswerService;
import servises.ValidatorService;
import validator.DataValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AnswerServiceTest {
    @Mock
    private AnswerRepo mockAnswerRepo;
    @Mock
    private ValidatorService mockValidatorService;

    AnswerService answerService;


    @BeforeEach
    public void setUp() {
        mockAnswerRepo = Mockito.mock(AnswerRepo.class);
        mockValidatorService = Mockito.mock(ValidatorService.class);
        answerService = new AnswerService(mockAnswerRepo, mockValidatorService);
    }


    @Test
    public void findAllByQuestionIdTest() throws DataBaseException {
        Answer answer = new Answer();
        answer.setQuestionId(1L);
        answer.setText("text");
        answer.setResult(true);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        Mockito.when(mockAnswerRepo.getAnswersByQuestionId(Mockito.anyLong())).thenReturn(answers);
        assertEquals(answers, answerService.getAnswers(1L));
    }

//    @Test
//    public void createAnswerServiceTest() throws DataBaseException, ValidateException {
//        Mockito.when(mockValidatorService.validateText(Mockito.anyString())).thenReturn(true);
//        Mockito.when(mockAnswerRepo.createAnswer(Mockito.anyLong(),
//                Mockito.anyString(), Mockito.anyBoolean())).thenReturn(12);
//
//        assertEquals(12, answerService.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean()));
//    }

    @Test
    public void deleteAnswerTest() throws DataBaseException {
        Mockito.when(mockAnswerRepo.delete(Mockito.anyLong())).thenReturn(1);
        assertEquals(1, answerService.deleteAnswer(23L));
    }

//    @Test
//    public void testThrows() throws DataBaseException {
//        Mockito.when(mockAnswerRepo
//                        .createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean()))
//                .thenThrow(DataBaseException.class);
//        ;
//        assertThrows(QuizException.class, () -> {
//            answerService.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean());
//        });
//    }


}
