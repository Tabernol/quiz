package command.get;

import command.FilterSupport;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.UserDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import servises.UserService;

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
public class FilterUsers implements RequestHandler, FilterSupport {

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

        String status = readAndSetParameterForFilter(req, STATUS, DEFAULT_FILTER_ALL);
        String order = readAndSetParameterForFilter(req, ORDER, DEFAULT_ORDER_NAME_ASC);
        String rows = readAndSetParameterForFilter(req, ROWS, DEFAULT_ROWS_5);
        String page = req.getParameter(PAGE) == null ? "1" : req.getParameter(PAGE);
//        ===========================================
        req.getSession().setAttribute(CURRENT_FILTER, req.getServletPath());

        List<UserDto> userList;
        try {
            userList = userService.nextPage(status, order, Integer.valueOf(rows), Integer.valueOf(page));
            req.setAttribute(USERS, userList);
            req.setAttribute(PAGE, page);
            req.setAttribute(COUNT_PAGES, userService.countPages(status, rows));
            log.info("filter users was used");
            req.getRequestDispatcher(ADMIN_USERS).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Trouble with using filter users ", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
