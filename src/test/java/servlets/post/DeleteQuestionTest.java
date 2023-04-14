package servlets.post;

import command.get.EditTest;
import command.post.DeleteQuestion;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.impl.QuestionServiceImpl;

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
        QuestionServiceImpl questionService = Mockito.mock(QuestionServiceImpl.class);

        DeleteQuestion deleteQuestion = new DeleteQuestion();
        EditTest editTest = new EditTest();

        String path = "/WEB-INF/view/admin/edit_test.jsp";

        Mockito.when(request.getParameter("page")).thenReturn("2");
        Mockito.when(request.getParameter("test_id")).thenReturn("3");
        Mockito.when(request.getParameter("question_id")).thenReturn("8");

        Mockito.when(questionService.delete(Mockito.anyLong())).thenReturn(12);
        Mockito.when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        deleteQuestion.execute(request,response);

        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(path);
        Mockito.verify(dispatcher).forward(request, response);
    }
}
