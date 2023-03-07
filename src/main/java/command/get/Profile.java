package command.get;

import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Test;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.ResultRepo;
import repo.TestRepo;
import repo.UserRepo;
import servises.ResultService;
import servises.TestService;
import servises.UserService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Profile.class provides information about User
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class Profile implements RequestHandler {
    private static Logger logger = LogManager.getLogger(Profile.class);

    /**
     * This method calls to FilterResul command
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
        logger.info("Go to filter result");
        FilterResult filterResult = new FilterResult();
        filterResult.execute(req, resp);
    }
}
