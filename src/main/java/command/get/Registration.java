package command.get;

import command.AbstractCommand;
import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Registration.class provides conditions for registration
 * This class is only allowed for guest
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class Registration extends AbstractCommand {
    /**
     * This method redirects to the registration page
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
        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
    }
}
