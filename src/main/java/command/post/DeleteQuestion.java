package command.post;

import command.get.EditTest;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteQuestion.class is allowed only for admin.
 * The meaning of the class is to delete a Question in an existing Question in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class DeleteQuestion implements RequestHandler {

    /**
     * This method is read parameter from request.
     * It calls the service layer to delete a Question
     * if DataBaseException is caught, redirects to error page.
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
        QuestionService questionService = AppContext.getInstance().getQuestionService();
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("test_id", req.getParameter("test_id"));
        String id = req.getParameter("question_id");

        try {
            questionService.delete(Long.valueOf(id));
            log.info("Question with id " + id + "has deleted");
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        } catch (DataBaseException e) {
            log.warn("Question with id " + id + "has not delete", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }


    }
}
