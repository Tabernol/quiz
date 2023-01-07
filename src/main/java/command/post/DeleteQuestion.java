package command.post;

import command.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestion implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("test_id", req.getParameter("test_id"));

        QuestionService questionService = new QuestionService();
        String id = req.getParameter("question_id");
        try {
            questionService.deleteQuestion(Long.valueOf(id));
            EditTest editTest = new EditTest();
            editTest.execute(req,resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }




    }
}
