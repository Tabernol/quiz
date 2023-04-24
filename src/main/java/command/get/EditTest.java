package command.get;

import command.AbstractCommand;
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
public class EditTest extends AbstractCommand {

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
        TestService testService = AppContext.getInstance().getTestService();
        QuestionService questionService = AppContext.getInstance().getQuestionService();
        Long id = Long.valueOf(req.getParameter(TEST_ID));

        req.setAttribute(PAGE, req.getParameter(PAGE));

        List<QuestionDto> all;

        try {
            TestDto testDto = testService.get(id);
            all = questionService.getAllById(id);
            req.setAttribute(TEST, testDto);
            req.setAttribute(QUESTIONS, all);
            log.info("Test with ID " + id + " has updated");
            req.getRequestDispatcher(EDIT_TEST).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Test with ID " + id + " has NOT updated ", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);

        }
    }
}
