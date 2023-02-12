package command.get;

import controllers.servlet.RequestHandler;
import repo.UserRepo;
import servises.UserService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterUsers implements RequestHandler {


    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService(new UserRepo(), new ValidatorService(new DataValidator()));

        String status = req.getParameter("status");
        String rows = req.getParameter("rows");
        String order = req.getParameter("order");


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



    }
}
