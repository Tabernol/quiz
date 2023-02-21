package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Finish.class is responsible for displaying the results of the completed test (quiz)
 * @author makskrasnopolskyi@gmail.com
 */
public class Finish implements RequestHandler {
    /**
     * This method redirects to page_finish.jsp page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/student/page_finish.jsp").forward(req, resp);
    }
}
