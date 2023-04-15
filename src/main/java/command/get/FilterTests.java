package command.get;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Test;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FilterTests.class is allowed for admin and student
 * The purpose of the class is to provide a sheet of tests(quizzes) with the selected filter and page
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class FilterTests implements RequestHandler {

    /**
     * This method contacts with service layer to retrieve the sheet of tests(quiz) with selected filter and page
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String sub = readAndSetParameterForFilter(req, SUB, DEFAULT_FILTER_ALL);
        String order = readAndSetParameterForFilter(req, ORDER, DEFAULT_ORDER_NAME_ASC);
        String rows = readAndSetParameterForFilter(req, ROWS, DEFAULT_ROWS_5);
        String page = req.getParameter(PAGE) == null ? "1" : req.getParameter(PAGE);
        String role = (String) req.getSession().getAttribute("role");
//        =======================================

        TestService testService = AppContext.getInstance().getTestService();
        List<String> subjects;
        List<Test> filterTests;
        int countPages;

        try {
            countPages = testService.countPages(sub, Integer.valueOf(rows));

            filterTests = testService.getPageTestList(sub, order, Integer.valueOf(rows), Integer.valueOf(page), role);
            subjects = testService.getDistinctSubjects();

            req.getSession().setAttribute("subjects", subjects);
            req.getSession().setAttribute("tests", filterTests);
            req.setAttribute("count_pages", countPages);
            req.setAttribute("page", page);
            if (role.equals("admin")) {
                log.info("admin uses filter test");
                req.getRequestDispatcher(ADMIN_TESTS).forward(req, resp);
            } else {
                log.info("student uses filter test");
                req.getRequestDispatcher(STUDENT_TESTS).forward(req, resp);
            }
        } catch (DataBaseException e) {
            log.warn("Trouble with using filter tests ", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
