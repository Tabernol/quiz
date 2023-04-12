package command.get;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.QuestionDto;
import dto.TestDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.QuestionRepoImpl;
import repo.impl.TestRepoImpl;
import servises.QuestionService;
import servises.TestService;
import servises.impl.QuestionServiceImpl;
import servises.impl.TestServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * EditTest.class is allowed only for admin.
 * The purpose of this class is to redirect to page for editing test(quiz).
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditTest implements RequestHandler {
    private TestService testService;
    private QuestionService questionService;

    /**
     * This method reads parameters.
     * He contacts the service level to get information about his test(quiz) and questions for it.
     * Set this information in request and calls it.
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
        testService = AppContext.getInstance().getTestService();
        questionService = AppContext.getInstance().getQuestionService();
        Long id = Long.valueOf(req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));

        List<QuestionDto> all;

        try {
            TestDto testDto = testService.get(id);
            all = questionService.getAllById(id);
            req.setAttribute("test", testDto);
            req.setAttribute("questions", all);
            log.info("Test with ID " + id + " has updated");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_test.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Test with ID " + id + " has NOT updated ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);

        }
    }
}
