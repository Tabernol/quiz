package command.get;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.UserDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.UserRepoImpl;
import servises.UserService;
import servises.impl.UserServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FilterUsers.class is allowed only for admin
 * The purpose of the class is to provide a sheet of users with the selected filter and page
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class FilterUsers implements RequestHandler {

    /**
     * This method contacts with service layer to retrieve the sheet of users with selected filter and page
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
        UserService userService = AppContext.getInstance().getUserService();

        String status = req.getParameter("status");
        String rows = req.getParameter("rows");
        String order = req.getParameter("order");
        String page = req.getParameter("page");
        //  String role = (String) req.getSession().getAttribute("role");

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


        List<UserDto> userList;
        try {
            userList = userService.nextPage(status, order, Integer.valueOf(rows), Integer.valueOf(page));
            req.setAttribute("users", userList);
            req.setAttribute("page", page);
            req.setAttribute("count_pages", userService.countPages(status, rows));
            log.info("filter users was used");
            req.getRequestDispatcher(ADMIN_USERS).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Trouble with using filter users ", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
