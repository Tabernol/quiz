package servlets.get;

import command.get.PageTest;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repo.TestRepo;
import servises.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageTestTest {
    PageTest pageTest = new PageTest();

    private static final String PATH_PAGE_TEST = "/WEB-INF/view/student/page_test.jsp";

    @Test
    public void pageTest() throws ServletException, IOException, DataBaseException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final TestService mockTestService = Mockito.mock(TestService.class);
        final TestRepo mockTestRepo = Mockito.mock(TestRepo.class);


        models.Test test = new models.Test();
        test.setId(12);
        test.setName("new");
        test.setSubject("math");
        test.setDifficult(12);
        test.setDuration(13);
        Mockito.when(request.getParameter("test_id")).thenReturn("12");
        Mockito.when(mockTestService.get(Mockito.anyLong())).thenReturn(test);

        Mockito.when(request.getRequestDispatcher(PATH_PAGE_TEST)).thenReturn(dispatcher);


        //pageTest.execute(request, response);
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher(PATH_PAGE_TEST);
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);
    }
}
