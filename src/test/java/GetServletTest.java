import command.AllUser;
import connection.MyDataSource;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import repo.UserRepo;
import servises.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GetServletTest {

//    @Test
//    public void testServlet() throws Exception {
//
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        when(request.getParameter("users")).thenReturn("list");
//       // when(request.getParameter("password")).thenReturn("secret");
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        //new AllUser().doPost(request, response);
//        new AllUser().execute(request, response);
//
//        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
//        writer.flush(); // it may not have been flushed yet...
//        assertTrue(stringWriter.toString().contains("My expected string"));
//    }
}
