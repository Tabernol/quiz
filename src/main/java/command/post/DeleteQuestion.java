package command.post;

import command.get.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestion implements RequestHandler {

    private static Logger logger = LogManager.getLogger(DeleteQuestion.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("test_id", req.getParameter("test_id"));

        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService());
        String id = req.getParameter("question_id");
        try {
            questionService.deleteQuestion(Long.valueOf(id));
            logger.info("Question with id " + id + "has deleted");
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Question with id " + id + "has not delete", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
