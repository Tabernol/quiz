package service_test;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Question;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionServiceTest {
    @Mock
    private QuestionRepo mockQuestionRepo = Mockito.mock(QuestionRepo.class);
    @Mock
    private ValidatorService mockValidatorService = Mockito.mock(ValidatorService.class);

    QuestionService questionService = new QuestionService(mockQuestionRepo, mockValidatorService);

    @Test
    public void getQuestionTest() throws DataBaseException {
        Question question = new Question();
        question.setId(12);
        Mockito.when(mockQuestionRepo.get(Mockito.anyLong())).thenReturn(question);
        Assertions.assertEquals(question, questionService.get(1L));
    }

    @Test
    public void getAllByIdTest() throws DataBaseException {
        Question question = new Question();
        question.setId(43L);
        List<Question> questions = new ArrayList<>();
        Mockito.when(mockQuestionRepo.getAllById(Mockito.anyLong())).thenReturn(questions);
        assertEquals(questions, questionService.getAllById(2L));
    }

    @Test
    public void addQuestionAddTest() throws DataBaseException, ValidateException {
        Mockito.when(mockQuestionRepo.createQuestion(Mockito.anyLong(), Mockito.anyString())).thenReturn(1);
        int i = questionService.addQuestion(Mockito.anyLong(), Mockito.anyString());
        assertEquals(1, i);

    }

//    @Test
//    public void deleteQuestionDeleteTest() throws DataBaseException {
//        Mockito.when(mockQuestionRepo.delete(Mockito.anyLong())).thenReturn(1);
//        int i = questionService.deleteQuestion(Mockito.anyLong());
//        assertEquals(1, i);
//    }
//
//    @Test
//    public void updateQuestionTest() throws DataBaseException, ValidateException {
//        Mockito.when(mockQuestionRepo.updateQuestion(Mockito.anyString(), Mockito.anyLong())).thenReturn(1);
//        int update = questionService.update(Mockito.anyString(), Mockito.anyLong());
//        assertEquals(1, update);
//    }
}
