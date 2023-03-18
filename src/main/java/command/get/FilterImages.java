package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.impl.ImageRepoImpl;
import servises.ImageService;

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
public class FilterImages implements RequestHandler {
    private ImageService imageService;

    private static Logger logger = LogManager.getLogger(FilterImages.class);

    /**
     * This method contacts with service layer to retrieve all information about images
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        imageService = new ImageService(new ImageRepoImpl());

        try {
            List<Image> all = imageService.getAll();
            req.setAttribute("all_images", all);
            logger.info("Set all images to request");
        } catch (DataBaseException e) {
            logger.warn("Failed to retrieve image from database ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }

        req.setAttribute("question_id", req.getParameter("question_id"));
        req.setAttribute("test_id", req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_images.jsp").forward(req, resp);
    }
}
