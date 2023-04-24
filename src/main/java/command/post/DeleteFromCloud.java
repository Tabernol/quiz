package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import command.AbstractCommand;
import command.get.FilterImages;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DeleteFromCloud extends AbstractCommand {

    /**
     * This method contacts with service layer to delete image from cloudinary and database
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String publicId = req.getParameter(PUBLIC_ID);

        ImageService imageService = AppContext.getInstance().getImageService();

        if (imageService.canDeleteImage(publicId).isEmpty()) {
            //delete image from cloudinary
            Cloudinary cloudinary = (Cloudinary) req.getServletContext().getAttribute(CLOUDINARY);
            Map deleteParams = ObjectUtils.asMap("invalidate", true);
            cloudinary.uploader().destroy(publicId, deleteParams);
            log.info("The Image with publicID {} was deleted from cloudinary", publicId);


            //delete data from database
            try {
                imageService.delete(publicId);
                log.info("The Image with publicID {}  was deleted from database", publicId);
                FilterImages filterImages = new FilterImages();
                filterImages.execute(req, resp);
            } catch (DataBaseException e) {
                log.info("There was a problem with deleting the image. publicID = {}", publicId);
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            }
        } else {
            List<String> nameTestWithDeletedURL = imageService.canDeleteImage(publicId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_images" +
                    "&message_bad_request=" + "Test with name: " + nameTestWithDeletedURL.get(0) + " contains this image");
        }


    }
}
