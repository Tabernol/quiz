package servlets.get;

import command.Home;
import org.apache.catalina.Session;
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

public class HomeTest {

    private final String HOME_PATH = "/WEB-INF/view/menu.jsp";

    Home home = new Home();

    @Test
    public void toHomeRoleAdmin() throws ServletException, IOException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession mockSession = Mockito.mock(HttpSession.class);


        Mockito.when(request.getRequestDispatcher(HOME_PATH)).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("role")).thenReturn("admin");

        home.execute(request, response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(HOME_PATH);
        Mockito.verify(dispatcher).forward(request, response);
        assertEquals("admin", request.getSession().getAttribute("role"));
        Mockito.when(mockSession.getAttribute("role")).thenReturn("student");
        assertEquals("student", request.getSession().getAttribute("role"));
    }

    @Test
    public void homeForUnknownRole() throws ServletException, IOException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession mockSession = Mockito.mock(HttpSession.class);

        Mockito.when(request.getRequestDispatcher("/")).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("role")).thenReturn(null);

        home.execute(request, response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("/");
        Mockito.verify(dispatcher).forward(request, response);
        assertNull(request.getSession().getAttribute("role"));
    }
}
