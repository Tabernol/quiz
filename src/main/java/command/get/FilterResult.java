package command.get;

import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.ResultRepoImpl;
import repo.impl.TestRepoImpl;
import repo.impl.UserRepoImpl;
import servises.ResultService;
import servises.TestService;
import servises.UserService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    private ResultService resultService;
    private TestService testService;
    private UserService userService;

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
        String sub = req.getParameter("sub");
        String order = req.getParameter("order");
        String rows = req.getParameter("rows");
        String page = req.getParameter("page");

        if (sub == null || order == null || rows == null) {
            sub = (String) req.getSession().getAttribute("sub");
            order = (String) req.getSession().getAttribute("order");
            rows = (String) req.getSession().getAttribute("rows");
            if (sub == null || order == null || rows == null) {
                sub = "all";
                order = "name asc";
                rows = "5";
                setParametersToSession(sub, order, rows, req, resp);
            }
        } else {
            setParametersToSession(sub, order, rows, req, resp);
        }


        if (page == null) {
            page = "1";
        }

        System.out.println("page = " + page);

        resultService = new ResultService(new ResultRepoImpl());
        testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));
        userService = new UserService(new UserRepoImpl(), new ValidatorService(new DataValidator()));
        List<String> subjects;
        int countPages;

        Long userId;
        if (req.getParameter("user_id") != null) {
            userId = Long.valueOf(req.getParameter("user_id"));
        } else {
            userId = (Long) req.getSession().getAttribute("user_id");
        }

        System.out.println("user id filter_result   " + userId);


        try {
            subjects = resultService.getDistinctSubject(userId);
            req.getSession().setAttribute("subjects", subjects);
            req.setAttribute("user", userService.get(userId));


            countPages = resultService.getCountPagesResult(userId, Integer.valueOf(rows), sub);
            System.out.println("count page = " + countPages);

            List<ResultDto> pageResultList = resultService
                    .getPageResultList(userId, sub, order, Integer.valueOf(rows), Integer.valueOf(page));

            System.out.println(pageResultList);
            if (countPages != 0 || !pageResultList.isEmpty()) {
                req.setAttribute("user_result", pageResultList);
                req.setAttribute("count_pages", countPages);
            }
//            req.setAttribute("user_id", userId);

            //    req.setAttribute("page", page);

            log.info("Filter result was used");
            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Trouble with using filter result ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }


    private void setParametersToSession(String sub, String order, String rows,
                                        HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.setAttribute("sub", sub);
        session.setAttribute("order", order);
        session.setAttribute("rows", rows);
    }
}
