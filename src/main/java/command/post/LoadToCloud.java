package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.impl.ImageRepoImpl;
import servises.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoadToCloud implements RequestHandler {

    private int count = 0;

    private ImageService imageService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        imageService = new ImageService(new ImageRepoImpl());

        String webInfPath = req.getServletContext().getRealPath("WEB-INF");
        String fullPath = webInfPath + "\\image" + (++count) + ".jpeg";

        System.out.println(fullPath);

        for (Part part : req.getParts()) {
            part.write(fullPath);
        }

        Cloudinary cloudinary = (Cloudinary) req.getServletContext().getAttribute("cloudinary");

        File file = new File(fullPath);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        try {
            imageService.addImage(
                    (String) uploadResult.get("public_id"),
                    (String) uploadResult.get("url"),
                    (Integer) uploadResult.get("width"),
                    (Integer) uploadResult.get("height"));
//
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/prg?servlet_path=/filter_images");
    }
}
