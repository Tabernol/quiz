package command.get;

import controllers.servlet.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Profile.class provides information about User
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class Profile implements RequestHandler {

    /**
     * This method calls to FilterResul command
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
        log.info("Go to filter result");
        FilterResult filterResult = new FilterResult();
        filterResult.execute(req, resp);
    }
}
