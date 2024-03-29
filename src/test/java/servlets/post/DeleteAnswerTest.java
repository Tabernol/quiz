package servlets.post;

import command.post.DeleteAnswer;
import dto.QuestionDto;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.impl.AnswerServiceImpl;
import servises.impl.QuestionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteAnswerTest {

    @Test
    public void deleteAnswer() throws DataBaseException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        AnswerServiceImpl answerService = Mockito.mock(AnswerServiceImpl.class);
        QuestionServiceImpl questionService = Mockito.mock(QuestionServiceImpl.class);

        QuestionDto questionDto = new QuestionDto();
        DeleteAnswer deleteAnswer = new DeleteAnswer();
        String path = "/WEB-INF/view/admin/edit_question.jsp";

        when(request.getParameter("test_id")).thenReturn("1");
        when(request.getParameter("question_id")).thenReturn("2");
        when(request.getParameter("answer_id")).thenReturn("3");
        when(request.getParameter("page")).thenReturn("4");
        Mockito.when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        Mockito.when(answerService.delete(Mockito.anyLong())).thenReturn(1);
        Mockito.when(questionService.get(Mockito.anyLong())).thenReturn(questionDto);


        assertEquals(1L, Long.valueOf(request.getParameter("test_id")));
        assertEquals(2L, Long.valueOf(request.getParameter("question_id")));
        assertEquals(3L, Long.valueOf(request.getParameter("answer_id")));
        assertEquals(4, Integer.valueOf(request.getParameter("page")));


        deleteAnswer.execute(request, response);

        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(path);
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);
    }
}

