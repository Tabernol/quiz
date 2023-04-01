package servlets.post;

import command.get.EditTest;
import command.get.FilterTests;
import command.post.DeleteQuestion;
import command.post.DeleteTest;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.QuestionService;
import servises.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class DeleteTestTest {
//    @Test
//    public void deleteTest() throws DataBaseException, ServletException, IOException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
//        final HttpSession session = Mockito.mock(HttpSession.class);
//        TestService testService = Mockito.mock(TestService.class);
//
//        DeleteTest deleteTest = new DeleteTest();
//        FilterTests filterTests = new FilterTests();
//
//        String path = "/WEB-INF/view/admin/admin_tests.jsp";
//
//        Mockito.when(request.getParameter("page")).thenReturn("2");
//        Mockito.when(request.getParameter("test_id")).thenReturn("3");
//
//        Mockito.when(testService.delete(Mockito.anyLong())).thenReturn(12);
//       // Mockito.when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
//
//        deleteTest.execute(request, response);
//
//        //Mockito.verify(request, Mockito.never()).getSession();
//        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(path);
//       // Mockito.verify(dispatcher).forward(request, response);
//    }
}
