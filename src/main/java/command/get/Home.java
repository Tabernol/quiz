package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Home.class is responsible for the home page in the API
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class Home implements RequestHandler {
    /**
     * This method reads the 'role' parameter from the session and redirects.
     * If role is unknown (guest) redirects to index.jsp page,
     * otherwise redirects to menu.jsp page
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            req.getRequestDispatcher("/").forward(req, resp);
        } else if (role.equals("admin") || role.equals("student")) {
            req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);
        }
    }
}
