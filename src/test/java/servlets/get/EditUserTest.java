//package servlets.get;
//
////import command.get.EditUser;
//import exeptions.DataBaseException;
//import models.User;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import servises.UserService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class EditUserTest {
//    EditUser editUser = new EditUser();
//    @Test
//    public void editUserTest() throws DataBaseException, ServletException, IOException {
//        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
//        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
//        UserService mockUserService = Mockito.mock(UserService.class);
//
//        User user = new User();
//
//        Mockito.when(request.getParameter("user_id")).thenReturn("12");
//        Mockito.when(mockUserService.get(Mockito.anyLong())).thenReturn(user);
//        Mockito.when(request.getRequestDispatcher("/WEB-INF/view/admin/edit_user.jsp")).thenReturn(dispatcher);
//
//        editUser.execute(request, response);
//        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("/WEB-INF/view/admin/edit_user.jsp");
//        Mockito.verify(request, Mockito.never()).getSession();
//        Mockito.verify(dispatcher).forward(request, response);
//    }
//}
