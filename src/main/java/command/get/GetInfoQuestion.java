package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetInfoQuestion implements RequestHandler {

    QuestionService questionService;

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        questionService = new QuestionService(new QuestionRepo(), new ValidatorService());
        String idQuestion = req.getParameter("id_question");
        System.out.println(idQuestion);
        String text;
        try {
            text = questionService.get(Long.valueOf(idQuestion)).getText();
        } catch (DataBaseException e) {
            throw new RuntimeException(e);

        }


        PrintWriter writer = resp.getWriter();
        writer.print(text);

    }
}
