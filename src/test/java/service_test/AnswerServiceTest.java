package service_test;

import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Answer;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.AnswerRepoImpl;
import servises.AnswerService;
import servises.ValidatorService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AnswerServiceTest {
    @Mock
    private AnswerRepoImpl mockAnswerRepoImpl;
    @Mock
    private ValidatorService mockValidatorService;

    private AnswerService answerService;

    private Answer answer;


    @BeforeEach
    public void setUp() {
        mockAnswerRepoImpl = Mockito.mock(AnswerRepoImpl.class);
        mockValidatorService = Mockito.mock(ValidatorService.class);
        answerService = new AnswerService(mockAnswerRepoImpl, mockValidatorService);
      //  answer = new Answer(12L, 123L, "text", true);
    }


    @Test
    public void findAllByQuestionIdTest() throws DataBaseException {
        Answer answer = new Answer();
        answer.setQuestionId(1L);
        answer.setText("text");
        answer.setResult(true);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        Mockito.when(mockAnswerRepoImpl.getAnswersByQuestionId(Mockito.anyLong())).thenReturn(answers);

        Set<AnswerDto> answerDtoSet = new HashSet<>();
        for (Answer ans : answers) {
            answerDtoSet.add(answerService.mapToDto(ans));
        }

        assertEquals(answerDtoSet, answerService.getAnswers(1L));
    }

    @Test
    public void createAnswerServiceTest() throws DataBaseException, ValidateException {
        Mockito.when(mockValidatorService.validateText(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockAnswerRepoImpl.create(Mockito.any(Answer.class))).thenReturn(12L);

        assertEquals(12, answerService.createAnswer(
                new AnswerDto(1L, 123L, "text", true)));
    }

    @Test
    public void deleteAnswerTest() throws DataBaseException {
        Mockito.when(mockAnswerRepoImpl.delete(Mockito.anyLong())).thenReturn(1);
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
