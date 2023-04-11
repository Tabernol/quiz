package service_test;

import dto.QuestionDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Question;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.QuestionRepoImpl;
import servises.QuestionService;
import servises.ValidatorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionServiceTest {
    @Mock
    private QuestionRepoImpl mockQuestionRepoImpl = Mockito.mock(QuestionRepoImpl.class);
    @Mock
    private ValidatorService mockValidatorService = Mockito.mock(ValidatorService.class);

    QuestionService questionService = new QuestionService(mockQuestionRepoImpl, mockValidatorService);

    @Test
    public void getQuestionTest() throws DataBaseException {
        Question question = new Question();
        question.setId(12L);
        Mockito.when(mockQuestionRepoImpl.get(Mockito.anyLong())).thenReturn(question);
        QuestionDto questionDto = questionService.mapToDto(question);
        Assertions.assertEquals(questionDto, questionService.get(1L));
    }

    @Test
    public void getAllByIdTest() throws DataBaseException {
        Question question = new Question();
        question.setId(43L);
        List<Question> questions = new ArrayList<>();
        Mockito.when(mockQuestionRepoImpl.getAllById(Mockito.anyLong())).thenReturn(questions);
        assertEquals(questions, questionService.getAllById(2L));
    }

    @Test
    public void addQuestionAddTest() throws DataBaseException, ValidateException {
        Mockito.when(mockQuestionRepoImpl.create(new Question())).thenReturn(1L);
        long i = questionService.addQuestion(new QuestionDto());
        assertEquals(1, i);

    }

    @Test
    public void deleteQuestionDeleteTest() throws DataBaseException {
        Mockito.when(mockQuestionRepoImpl.delete(Mockito.anyLong())).thenReturn(12);
        int i = questionService.deleteQuestion(Mockito.anyLong());
        assertEquals(12, i);
    }

    //
    @Test
    public void updateQuestionTest() throws DataBaseException, ValidateException {
        Mockito.when(mockQuestionRepoImpl.update(Mockito.any(Question.class))).thenReturn(12);
        int update = questionService.update(new QuestionDto(123L, "newText"));
        assertEquals(12, update);
    }

    @Test
    public void updateImageTest() throws DataBaseException, ValidateException {
        Mockito.when(mockQuestionRepoImpl.updateImageQuestion(Mockito.anyString(), Mockito.anyLong())).thenReturn(13);
        int update = questionService.updateImage("newURL", 34L);
        assertEquals(13, update);
    }
}
