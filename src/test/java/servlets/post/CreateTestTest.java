package servlets.post;

import command.post.CreateTest;
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
import static org.mockito.Mockito.when;

public class CreateTestTest {
    CreateTest createTest = new CreateTest();
    @Test
    public void createTest() throws DataBaseException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        TestService mockTestService = mock(TestService.class);



        when(request.getParameter("name")).thenReturn("first");
        when(request.getParameter("subject")).thenReturn("math");
        when(request.getParameter("difficult")).thenReturn("30");
        when(request.getParameter("duration")).thenReturn("4");

//        Mockito.when(mockValidateMessage.checkFields(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt()))
//                        .thenReturn("All Right");

//        Mockito.when(mockTestService
//                .createTest(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt()))
//                        .thenReturn(1);

        createTest.execute(request,response);

       // Mockito.when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

    }
}
