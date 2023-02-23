package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Image;
import repo.ImageRepo;
import servises.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FilterImages implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("filter Images");
        ImageService imageService = new ImageService(new ImageRepo());
        try {
            List<Image> all = imageService.getAll();
            req.setAttribute("all_images", all);
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("question_id", req.getParameter("question_id"));
        req.setAttribute("test_id", req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_images.jsp").forward(req, resp);
    }
}
