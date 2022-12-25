package command.post;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTestPost implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        String difficult = req.getParameter("difficult");
        String duration = req.getParameter("duration");



    }
}
