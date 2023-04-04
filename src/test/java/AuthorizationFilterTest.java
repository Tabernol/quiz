
import connection.MyDataSource;
import controllers.filters.AuthorizationFilter;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import servises.PasswordHashingService;
import servises.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.reCaptcha.VerifyRecaptcha;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthorizationFilterTest {

    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;
    @InjectMocks
    private AuthorizationFilter filter;

    private static final String LOGIN = "user";
    private static final String PASSWORD = "password";
    private static final long USER_ID = 1;

    @BeforeEach
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        userService = Mockito.mock(UserService.class);
        session = Mockito.mock(HttpSession.class);
        filter = new AuthorizationFilter();
        dispatcher = Mockito.mock(RequestDispatcher.class);


        when(request.getParameter("login")).thenReturn(LOGIN);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/view/login_form.jsp")).thenReturn(dispatcher);
    }

    @Test
    public void testDoCustomFilter_ValidInputFailWithRecaptcha() throws ServletException, IOException, ValidateException, DataBaseException {
        User user = new User();
        user.setId(USER_ID);
        user.setPassword(PASSWORD);

        when(userService.getId(anyString())).thenReturn(USER_ID);
        when(userService.get(USER_ID)).thenReturn(user);
        when(userService.isCorrectPassword(USER_ID, PASSWORD)).thenReturn(true);
        when(request.getParameter("g-recaptcha-response")).thenReturn("valid_recaptcha_response");

        MockedStatic<VerifyRecaptcha> verifyRecaptchaMockedStatic = mockStatic(VerifyRecaptcha.class);
        verifyRecaptchaMockedStatic.when(() -> VerifyRecaptcha.verify("verify")).thenReturn(true);

        MockedStatic<PasswordHashingService> passwordHashingServiceMockedStatic = mockStatic(PasswordHashingService.class);
        passwordHashingServiceMockedStatic.when(() ->
                PasswordHashingService.validatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(true);


        filter.doCustomFilter(request, response, filterChain);
        Mockito.verify(request, Mockito.times(1))
                .getRequestDispatcher("/WEB-INF/view/login_form.jsp");

//        verify(session).setAttribute("user_id", USER_ID);
//        verify(session).setAttribute("name", null);
//        verify(session).setAttribute("role", null);
//        verify(filterChain).doFilter(request, response);
    }

//    @Test
//    public void testDoCustomFilter_InvalidInput_Failure() throws ServletException, IOException, ValidateException, DataBaseException {
//        when(request.getParameter("login")).thenReturn("");
//        when(request.getParameter("password")).thenReturn("");
//        when(request.getParameter("g-recaptcha-response")).thenReturn("invalid_recaptcha_response");
//        when(VerifyRecaptcha.verify("invalid_recaptcha_response")).thenReturn(false);
//
//        filter.doCustomFilter(request, response, filterChain);
//
//        verify(request).setAttribute("message_bad_request", "You may have forgotten to enter your input data");
//        verify(request).getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(request, response);
//    }
}
