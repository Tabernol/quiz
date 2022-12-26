package command.post;

import command.EditTest;
import controllers.servlet.RequestHandler;
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
        QuestionService questionService = new QuestionService();
        String id = req.getParameter("question_id");
        System.out.println(id);
        questionService.deleteQuestion(Long.valueOf(id));

        String test_id = req.getParameter("test_id");
        req.setAttribute("test_id", test_id);

        EditTest editTest = new EditTest();
        editTest.execute(req,resp);


    }
}
