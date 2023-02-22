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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoadToCloud implements RequestHandler {

    int count = 0;

    private ImageService imageService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        imageService = new ImageService(new ImageRepo());

        String webInfPath = req.getServletContext().getRealPath("WEB-INF");
        String fullPath = webInfPath + "\\image" + (++count) + ".jpeg";

        System.out.println(fullPath);

        for (Part part : req.getParts()) {
            part.write(fullPath);
        }

        Cloudinary cloudinary = (Cloudinary) req.getServletContext().getAttribute("cloudinary");

        File file = new File(fullPath);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        int i = 0;
        for (Object item : uploadResult.keySet()) {

            System.out.print(++i + " = " + item.toString() + " , ");
        }
        System.out.println("=========================");
        int k = 0;
        for (Object item : uploadResult.values()) {
            System.out.print(++k + " = " + item.toString() + " , ");
        }

        try {
            imageService.addImage(
                    (String) uploadResult.get("public_id"),
                    (String) uploadResult.get("url"),
                    (Integer) uploadResult.get("width"),
                    (Integer) uploadResult.get("height"));
//
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }


        resp.getWriter().print("The file uploaded sucessfully.");
    }
}
