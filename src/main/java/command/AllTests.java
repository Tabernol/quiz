//package command;
//
//import constans.Sort;
//import controllers.servlet.RequestHandler;
//import models.Test;
//import servises.TestService;
//import servises.UserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class AllTests implements RequestHandler {
//    @Override
//    public void execute(HttpServletRequest req,
//                        HttpServletResponse resp)
//            throws ServletException, IOException {
//        TestService testService = new TestService();
//
//        List<String> subjects = testService.getDistinctSubjects();
//        List<String> sorts = Arrays.asList("difficult desc", "difficult asc", "name desc");
//
//
//        req.getSession().setAttribute("subjects", subjects);
//        req.getSession().setAttribute("orders", sorts);
//
//        List<Test> all = testService.getAll();
//        req.setAttribute("tests", all);
//
//        Long userId = (Long) req.getSession().getAttribute("user_id");
//        UserService userService = new UserService();
//        String roleById = userService.getRoleById(userId);
//
//
//        req.getRequestDispatcher("/WEB-INF/view/" + roleById + "/" + roleById + "_tests.jsp").forward(req, resp);
//
//    }
//}
