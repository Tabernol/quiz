
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import servises.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilterTest {
    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    UserService userService;

    @BeforeEach
    public void setUp(){
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        userService = Mockito.mock(UserService.class);
    }
//
//    @Test
//    public void AuthFilterTest() {
//        Mockito.when(request.getSession()).thenReturn(session);
//        Mockito.when(request.getParameter(Mockito.anyString())).thenReturn("parameter");
//
//    }

}
