package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * UpLoadImage.class is responsible for accept image and passing to service layer to load in cloud
 */
public class UpLoadImage implements RequestHandler {
    QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String questionId = req.getParameter("question_id");
        String webInfPath = req.getServletContext().getRealPath("WEB-INF");


//        Part file = req.getPart("file");
//        String contentType = file.getContentType();
//        contentType = contentType.substring(contentType.indexOf("/")).replace("/", ".");

        String fullPath = webInfPath + "\\question" + questionId + ".jpeg";
        System.out.println(fullPath);

        for (Part part : req.getParts()) {
            part.write(fullPath);
        }

        Cloudinary cloud = (Cloudinary) req.getServletContext().getAttribute("cloudinary");

        File file = new File(fullPath);
        Map uploadResult = cloud.uploader().upload(file, ObjectUtils.emptyMap());
        int i = 0;
        for (Object item : uploadResult.keySet()) {

            System.out.print(++i + " = " + item.toString() + " , ");
        }
        System.out.println("=========================");
        int k = 0;
        for (Object item : uploadResult.values()) {
            System.out.print(++k + " = " + item.toString() + " , ");
        }

//        String url = (String) uploadResult.get("url");
//        try {
//            questionService.updateImage(url, Long.valueOf(questionId));
//        } catch (DataBaseException e) {
//            throw new RuntimeException(e);
//        } catch (ValidateException e) {
//            throw new RuntimeException(e);
//        }


        resp.sendRedirect(req.getContextPath() + "/prg?servlet_path=/filter_images");
    }
}
