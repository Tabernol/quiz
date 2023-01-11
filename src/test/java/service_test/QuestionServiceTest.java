package service_test;

import exeptions.DataBaseException;
import exeptions.QuizException;
import models.Answer;
import models.Question;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.AnswerRepo;
import repo.QuestionRepo;
import servises.AnswerService;
import servises.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionServiceTest {
    @Mock
    private QuestionRepo questionRepo = Mockito.mock(QuestionRepo.class);

    QuestionService questionService = new QuestionService(questionRepo);


    @Test
    public void getQuestionTest() throws DataBaseException {
        Question question = new Question();
        question.setId(12);
        Mockito.when(questionRepo.get(Mockito.anyLong())).thenReturn(question);
        Assertions.assertEquals(question, questionService.get(1L));
    }

    @Test
    public void getAllByIdTest() throws DataBaseException {
        Question question = new Question();
        question.setId(43L);
        List<Question> questions = new ArrayList<>();
        Mockito.when(questionRepo.getAllById(Mockito.anyLong())).thenReturn(questions);
        assertEquals(questions, questionService.getAllById(2L));
    }
}
