package command.get;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import servises.ResultService;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FilterResults.class is allowed for admin and student
 * The purpose of the class is to provide a sheet of user`s results with the selected filter
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class FilterResult implements RequestHandler {

    /**
     * This method contacts with service layer to retrieve the sheet of results with selected filter and page
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
        String sub = readAndSetParameterForFilter(req, SUB, DEFAULT_FILTER_ALL);
        String order = readAndSetParameterForFilter(req, ORDER, DEFAULT_ORDER_NAME_ASC);
        String rows = readAndSetParameterForFilter(req, ROWS, DEFAULT_ROWS_5);
        String page = req.getParameter(PAGE) == null ? "1" : req.getParameter(PAGE);


        ResultService resultService = AppContext.getInstance().getResultService();
        UserService userService = AppContext.getInstance().getUserService();
        List<String> subjects;
        int countPages;

        Long userId;
        if (req.getParameter("user_id") != null) {
            userId = Long.valueOf(req.getParameter("user_id"));
        } else {
            userId = (Long) req.getSession().getAttribute("user_id");
        }


        try {
            subjects = resultService.getDistinctSubject(userId);
            req.getSession().setAttribute("subjects", subjects);
            req.setAttribute("user", userService.get(userId));


            countPages = resultService.getCountPagesResult(userId, Integer.valueOf(rows), sub);

            List<ResultDto> pageResultList = resultService
                    .getPageResultList(userId, sub, order, Integer.valueOf(rows), Integer.valueOf(page));

            if (countPages != 0 || !pageResultList.isEmpty()) {
                req.setAttribute("user_result", pageResultList);
                req.setAttribute("count_pages", countPages);
            }

            log.info("Filter result was used");
            req.getRequestDispatcher(PROFILE).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Trouble with using filter result ", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
