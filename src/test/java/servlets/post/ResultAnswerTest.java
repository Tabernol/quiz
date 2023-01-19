package servlets.post;

import command.post.ResultAnswer;
import exeptions.DataBaseException;
import models.Answer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.ResultService;
import servises.TestService;
import util.ValidateMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultAnswerTest {
    ResultAnswer resultAnswer = new ResultAnswer();
    @Test
    public void resultAnswerTest() throws DataBaseException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        ResultService mockResultService = mock(ResultService.class);

        String[] result = {"1", "on"};
        List<Answer> answerList = new ArrayList<>();
        when(request.getParameter("id_question")).thenReturn("12");
        when(request.getParameterValues("res")).thenReturn(result);
        when(request.getParameter("number_question")).thenReturn("0");
      //  when(mockResultService.getResultByQuestion(Mockito.anyLong(), result)).thenReturn(true);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("result_test")).thenReturn(answerList);
        Mockito.when(session.getAttribute("size")).thenReturn(1);

        resultAnswer.execute(request,response);






    }
}
