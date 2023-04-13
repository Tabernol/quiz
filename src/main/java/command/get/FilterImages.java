package command.get;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Image;
import repo.impl.ImageRepoImpl;
import servises.ImageService;
import servises.impl.ImageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FilterImages.class is allowed only for admin
 * The purpose of the class is to provide a sheet of images with the selected filter
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class FilterImages implements RequestHandler {

    /**
     * This method contacts with service layer to retrieve all information about images
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageService imageService = AppContext.getInstance().getImageService();

        try {
            List<Image> all = imageService.getAll();
            req.setAttribute("all_images", all);
            log.info("Set all images to request");
        } catch (DataBaseException e) {
            log.warn("Failed to retrieve image from database ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }

        req.setAttribute("question_id", req.getParameter("question_id"));
        req.setAttribute("test_id", req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_images.jsp").forward(req, resp);
    }
}
