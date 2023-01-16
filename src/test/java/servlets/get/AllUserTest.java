package servlets.get;

import command.AllUser;
import exeptions.DataBaseException;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.UserRepo;
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
        Mockito.when(mockUserService.getAll()).thenReturn(allUsers);
        Mockito.when(mockSession.getAttribute("role")).thenReturn("admin");

        assertEquals(allUsers, mockUserService.getAll());
     //   allUser.execute(request, response);
//        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(ALL_USER_PATH);
        //  Mockito.verify(dispatcher).forward(request, response);
        // assertEquals(allUsers, mockUserService.getAll());
        request.setAttribute("users", allUsers);
    }
}
