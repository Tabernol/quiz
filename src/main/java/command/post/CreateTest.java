package command.post;

import controllers.servlet.RequestHandler;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTest implements RequestHandler {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long subjectId = Long.valueOf(req.getParameter("subject_id"));
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        TestService testService = new TestService();
        testService.createTest(name, subjectId, difficult, duration);
       // req.getRequestDispatcher()
    }
}
