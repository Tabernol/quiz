package command.get;

import command.AbstractCommand;
import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Finish.class is responsible for displaying the results of the completed test (quiz)
 * This class is only allowed for student
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class Finish extends AbstractCommand {
    /**
     * This method redirects to page_finish.jsp page
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
        req.getRequestDispatcher(PAGE_FINISH).forward(req, resp);
    }
}
