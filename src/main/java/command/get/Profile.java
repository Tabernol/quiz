package command.get;

import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Test;
import models.User;
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

public class Profile implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        FilterResult filterResult = new FilterResult();
        filterResult.execute(req, resp);

//        String sub = req.getParameter("sub");
//        String order = req.getParameter("order");
//        String rows = req.getParameter("rows");
//        String page = req.getParameter("page");
//
//        if (sub == null || order == null || rows == null|| page==null) {
//            sub = "all";
//            order = "name asc";
//            rows = "5";
//            page = "1";
//        }
//
//        HttpSession session = req.getSession();
//        session.setAttribute("sub", sub);
//        session.setAttribute("order", order);
//        session.setAttribute("rows", rows);
//
//
//        ResultService resultService = new ResultService(new ResultRepo());
//        TestService testService = new TestService(new TestRepo(), new ValidatorService());
//        UserService userService = new UserService(new UserRepo(), new ValidatorService());
//        List<String> subjects;
//
//        int countPages;
//
//        Long userId = (Long) req.getSession().getAttribute("user_id");
//        try {
//            countPages = resultService.getCountPagesResult(userId, Integer.valueOf(rows));
//            System.out.println("count page = " + countPages);
//
//            List<ResultDto> pageResultList = resultService
//                    .getPageResultList(userId, sub, order, Integer.valueOf(rows), Integer.valueOf(page));
//
//            System.out.println(pageResultList);
//
//            subjects = testService.getDistinctSubjects();
//            req.getSession().setAttribute("subjects", subjects);
//            req.getSession().setAttribute("tests", pageResultList);
//            req.setAttribute("count_pages", countPages);
//            req.setAttribute("page", page);
//            req.setAttribute("user_result", pageResultList);
//
//            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
//        } catch (DataBaseException e) {
//            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
//            throw new RuntimeException(e);
//        }


        //===============================================================

//        Long userId = (Long) req.getSession().getAttribute("user_id");
//
//        UserService userService = new UserService(new UserRepo(), new ValidatorService());
//        ResultService resultService = new ResultService(new ResultRepo());
//
//        User user = null;
//        List<ResultDto> resultByUser = null;
//        try {
//            user = userService.get(userId);
//            resultByUser = resultService.getResultByUser(user.getId());
//            req.setAttribute("name", user.getName());
//            req.setAttribute("user_result", resultByUser);
//
//            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
//        } catch (DataBaseException e) {
//            req.getRequestDispatcher("/WEB-INF/view/error_page.jsp").forward(req, resp);
//            throw new RuntimeException(e);
//        }
    }
}
