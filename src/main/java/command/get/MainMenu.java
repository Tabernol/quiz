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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);
    }
}
