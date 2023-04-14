package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MainMenu.class is responsibility for exit User to main menu of API
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class MainMenu implements RequestHandler {
    /**
     * This method redirects to menu.jsp page
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
        req.getRequestDispatcher(MENU).forward(req, resp);
    }
}
