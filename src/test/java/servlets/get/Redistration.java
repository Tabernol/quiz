package servlets.get;

import command.Registration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redistration {
    @Test
    public void toRegistration() throws ServletException, IOException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher("/WEB-INF/view/registration.jsp")).thenReturn(dispatcher);
        Registration registration = new Registration();
        registration.execute(request, response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("/WEB-INF/view/registration.jsp");
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);
    }
}
