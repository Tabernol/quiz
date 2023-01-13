package servlets.get;

import command.LoginForm;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFormTest {
    private final static String PATH_LOGIN_FORM = "/WEB-INF/view/login_form.jsp";

    private final LoginForm loginForm = new LoginForm();

    @Test
    public void loginFormTo() throws ServletException, IOException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher(PATH_LOGIN_FORM)).thenReturn(dispatcher);

        loginForm.execute(request, response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(PATH_LOGIN_FORM);
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);
    }
}
