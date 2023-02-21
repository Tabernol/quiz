package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginForm.class supports user authentication in the API
 * @author makskrasnopolskyi@gmail.com
 */
public class LoginForm implements RequestHandler {
    /**
     * This method redirects to login_form.jsp page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
    }
}
