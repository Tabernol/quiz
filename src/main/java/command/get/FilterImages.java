package command.get;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.ImageDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FilterImages extends AbstractCommand {

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
            List<ImageDto> all = imageService.getAll();
            req.setAttribute(IMAGES, all);
            log.info("Set all images to request");
        } catch (DataBaseException e) {
            log.warn("Failed to retrieve image from database ", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        req.setAttribute(QUESTION_ID, req.getParameter(QUESTION_ID));
        req.setAttribute(TEST_ID, req.getParameter(TEST_ID));
        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.getRequestDispatcher(ADMIN_IMAGES).forward(req, resp);
    }
}
