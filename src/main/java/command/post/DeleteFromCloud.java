package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import command.get.FilterImages;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.ImageRepo;
import servises.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * DeleteFromCloud.class is allowed only for admin.
 * This class helps delete image from cloud
 *
 * @author makskrasnopolskyi@gmail.com
 */

public class DeleteFromCloud implements RequestHandler {
    private static Logger logger = LogManager.getLogger(DeleteFromCloud.class);
    private ImageService imageService;

    /**
     * This method contacts with service layer to delete image from cloudinary and database
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String publicId = req.getParameter("public_id");

        //delete image from cloudinary
        Cloudinary cloudinary = (Cloudinary) req.getServletContext().getAttribute("cloudinary");
        Map deleteParams = ObjectUtils.asMap("invalidate", true);
        cloudinary.uploader().destroy(publicId, deleteParams);
        logger.info("The Image with publicID " + publicId + " was deleted from cloudinary");


        //delete data from database
        imageService = new ImageService(new ImageRepo());
        try {
            imageService.deleteImage(publicId);
            logger.info("The Image with publicID " + publicId + " was deleted from database");
            FilterImages filterImages = new FilterImages();
            filterImages.execute(req, resp);
        } catch (DataBaseException e) {
            logger.info("There was a problem with deleting the image. publicID = " + publicId);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
