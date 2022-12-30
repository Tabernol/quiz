package command.post;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ResultAnswer implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String idQuestion = req.getParameter("id_question");
        String[] res = req.getParameterValues("res");

        System.out.println("id quest = " + idQuestion);
        System.out.println(Arrays.toString(res));

    }
}
