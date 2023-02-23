package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.ImageRepo;
import servises.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DeleteFromCloud implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String publicId = req.getParameter("public_id");
        Cloudinary cloudinary = (Cloudinary) req.getServletContext().getAttribute("cloudinary");
        Map deleteParams = ObjectUtils.asMap("invalidate", true );

        cloudinary.uploader().destroy(publicId,deleteParams );
        System.out.println("need to Remove from DB");

        ImageService imageService = new ImageService(new ImageRepo());
        try {
            imageService.deleteImage(publicId);
        } catch (DataBaseException e) {




        }
    }
}
