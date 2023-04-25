package servlets.post;

import command.post.CreateTest;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.impl.TestServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateTestTest {
    private final CreateTest createTest = new CreateTest();
    @Test
    public void createTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("first");
        when(request.getParameter("subject")).thenReturn("math");
        when(request.getParameter("difficult")).thenReturn("30");
        when(request.getParameter("duration")).thenReturn("4");

        createTest.execute(request,response);
    }
}
