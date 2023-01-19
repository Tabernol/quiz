package servlets.post;

import command.post.DeleteAnswer;
import command.post.DeleteUser;
import exeptions.DataBaseException;
import models.Question;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.AnswerService;
import servises.QuestionService;
import servises.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteUserTest {
    DeleteUser deleteUser = new DeleteUser();

    @Test
    public void deleteUserTest() throws DataBaseException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        //   final HttpSession session = Mockito.mock(HttpSession.class);
        UserService mockUserService = mock(UserService.class);

        List<User> userList = new ArrayList<>();

        when(request.getParameter("user_id")).thenReturn("1");

        Mockito.when(request.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp")).thenReturn(dispatcher);
        Mockito.when(mockUserService.deleteUser(Mockito.anyLong())).thenReturn(1);
        Mockito.when(mockUserService.getAll()).thenReturn(userList);

        assertEquals(1L, Long.valueOf(request.getParameter("user_id")));

        deleteUser.execute(request, response);

        Mockito.verify(request, Mockito.times(1))
                .getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp");
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);
    }
}
