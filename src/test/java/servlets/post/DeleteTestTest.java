package servlets.post;

import command.post.DeleteTest;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class DeleteTestTest {
    @Test
    public void deleteTest() throws DataBaseException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        TestService testService = Mockito.mock(TestService.class);

        DeleteTest deleteTest = new DeleteTest();


        Mockito.when(request.getParameter("page")).thenReturn("2");
        Mockito.when(request.getParameter("test_id")).thenReturn("4");
        Mockito.when(testService.delete(Mockito.anyLong())).thenReturn(1);
        Mockito.when(request.getSession()).thenReturn(session);
//        Mockito.when(session.setAttribute(Mocki))

        deleteTest.execute(request,response);


    }
}
