package servlets.post;

import command.post.AddAnswer;
import command.post.DeleteAnswer;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Question;
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
import static org.mockito.Mockito.when;

public class AddAnswerTest {
    @Test
    public void AddAnswerTest1() throws ValidateException, DataBaseException, IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        AnswerService answerService = Mockito.mock(AnswerService.class);
//        QuestionService questionService = Mockito.mock(QuestionService.class);

        AddAnswer addAnswer = new AddAnswer();


        when(request.getContextPath()).thenReturn("path");
        when(request.getParameter("test_id")).thenReturn("1");
        when(request.getParameter("question_id")).thenReturn("2");
        when(request.getParameter("page")).thenReturn("4");
        when(request.getParameter("text")).thenReturn("text");
        when(request.getParameter("result")).thenReturn("result");

        String path = "WEB-INF/view/error_page.jsp";

        Mockito.when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        Mockito.when(answerService.createAnswer(Mockito.anyLong(),
                Mockito.anyString(), Mockito.anyBoolean())).thenThrow(new DataBaseException("test"));


        addAnswer.execute(request,response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(path);



    }
}
