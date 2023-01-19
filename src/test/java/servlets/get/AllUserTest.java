package servlets.get;

import command.get.AllUser;
import exeptions.DataBaseException;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import servises.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllUserTest {
    private final String ALL_USER_PATH = "/WEB-INF/view/admin/admin_users.jsp";
    private final String ERROR = "WEB-INF/view/error_page.jsp";

    AllUser allUser = new AllUser();
    @Mock
    UserService mockUserService;

    @Test
    public void allUsers() throws ServletException, IOException, DataBaseException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession mockSession = Mockito.mock(HttpSession.class);
        final UserService mockUserService = Mockito.mock(UserService.class);

        List<User> allUsers = new ArrayList<>();
        Mockito.when(request.getRequestDispatcher(ALL_USER_PATH)).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(mockSession);
        Mockito.when(request.getRequestDispatcher(ALL_USER_PATH)).thenReturn(dispatcher);
        Mockito.when(mockUserService.getAll()).thenReturn(allUsers);

        allUser.execute(request, response);

        assertEquals(allUsers, mockUserService.getAll());
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(ALL_USER_PATH);
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);

    }

//    @Test
//    public void allUserThrowEx() throws DataBaseException, ServletException, IOException {
//        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
//        final UserService mockUserService = Mockito.mock(UserService.class);

    //  Mockito.when(new UserService(new UserRepo())).thenReturn(mockUserService);
//        Mockito.when(mockUserService.getAll()).thenThrow(new DataBaseException("test"));
//        Mockito.when(request.getRequestDispatcher(ERROR)).thenReturn(dispatcher);

    //    allUser.execute(request,response);
//        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(ERROR);
//        Mockito.verify(request, Mockito.never()).getSession();
//        Mockito.verify(dispatcher).forward(request, response);
//        Assertions.assertThrows(DataBaseException.class, () -> {
//            allUser.execute(request,response);
//        });
    // }
}
