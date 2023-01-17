package servlets.post;

import command.EditTest;
import command.post.DeleteQuestion;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class DeleteQuestionTest {
    @Test
    public void deleteQuestionTest() throws DataBaseException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        QuestionService questionService = Mockito.mock(QuestionService.class);

        DeleteQuestion deleteQuestion = new DeleteQuestion();
        EditTest editTest = new EditTest();


        Mockito.when(request.getParameter("page")).thenReturn("2");
        Mockito.when(request.getParameter("test_id")).thenReturn("3");
        Mockito.when(request.getParameter("question_id")).thenReturn("8");
    //    Mockito.when(request.getRequestDispatcher())
        Mockito.when(questionService.deleteQuestion(Mockito.anyLong())).thenReturn(1);
      //  Mockito.when(editTest.execute(request,response)).thenReturn()

        deleteQuestion.execute(request,response);
    }
}
