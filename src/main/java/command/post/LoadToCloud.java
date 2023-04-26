package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import command.AbstractCommand;
import context.AppContext;
import dto.ImageDto;
import exeptions.DataBaseException;

import lombok.extern.slf4j.Slf4j;
import servises.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * LoadToCloud.class is allowed only for admin.
 * The meaning of the class is to load image to cloud
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class LoadToCloud extends AbstractCommand {

    /**
     * Executes the image upload process, saving the uploaded image to a file, uploading the file to a
     * cloud storage service, and adding the image to the database via the ImageService.
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ImageService imageService = AppContext.getInstance().getImageService();

        String webInfPath = req.getServletContext().getRealPath(WEB_INF);
        DateTimeFormatter custom = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String fullPath = webInfPath + "\\image" + LocalDateTime.now().format(custom) + ".jpeg";
        log.info("create path for load image {}", fullPath);

        for (Part part : req.getParts()) {
            part.write(fullPath);
        }

        Cloudinary cloudinary = (Cloudinary) req.getServletContext().getAttribute(CLOUDINARY);

        File file = new File(fullPath);
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        ImageDto imageDto = ImageDto.builder()
                .publicId((String) uploadResult.get(PUBLIC_ID))
                .url((String) uploadResult.get(URL))
                .width((Integer) uploadResult.get(WIDTH))
                .height((Integer) uploadResult.get(HEIGHT))
                .build();
        try {
            imageService.addImage(imageDto);
        } catch (DataBaseException e) {
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/prg?servlet_path=/filter_images");
    }
}
