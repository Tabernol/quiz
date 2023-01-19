package servlets.get;

import command.get.ToCreateTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCreateTestTest {
    @Test
    public void to() throws ServletException, IOException {
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher("WEB-INF/view/admin/create_test.jsp")).thenReturn(dispatcher);

        ToCreateTest toCreateTest = new ToCreateTest();
        toCreateTest.execute(request, response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("WEB-INF/view/admin/create_test.jsp");
        Mockito.verify(dispatcher).forward(request, response);
    }
}
