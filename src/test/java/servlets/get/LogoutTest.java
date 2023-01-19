package servlets.get;

import command.get.Logout;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LogoutTest {
    @Test
    public void logoutTest() throws ServletException, IOException {
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestDispatcher("/")).thenReturn(dispatcher);


        Logout logout = new Logout();
        logout.execute(request, response);

        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("/");
        Mockito.verify(request, Mockito.times(1)).getSession();
        Mockito.verify(dispatcher).forward(request, response);

    }
}
