package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.UserService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FilterUsers implements RequestHandler {

    private static Logger logger = LogManager.getLogger(FilterUsers.class);
    private UserService userService;

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        userService = new UserService(new UserRepo(), new ValidatorService(new DataValidator()));

        String status = req.getParameter("status");
        String rows = req.getParameter("rows");
        String order = req.getParameter("order");
        String page = req.getParameter("page");

        if (status == null || rows == null || order == null) {
            status = (String) req.getSession().getAttribute("status");
            rows = (String) req.getSession().getAttribute("rows");
            order = (String) req.getSession().getAttribute("order");
            if (status == null || rows == null || order == null) {
                status = "all";
                rows = "5";
                order = "name asc";
            }
        } else {
            req.getSession().setAttribute("status", status);
            req.getSession().setAttribute("rows", rows);
            req.getSession().setAttribute("order", order);
        }

        if (page == null) {
            page = "1";
        }


        List<User> userList;
        try {
            userList = userService.nextPage(status, order, rows, page);
            req.setAttribute("users", userList);
            req.setAttribute("page", page);
            req.setAttribute("count_pages", userService.countPages(status, rows));
            logger.info("filter users was used");
            req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Trouble with using filter users ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
