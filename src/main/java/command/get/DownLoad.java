package command.get;

import controllers.servlet.RequestHandler;
import util.MyPdfWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownLoad implements RequestHandler {
    private final int ARBITARY_SIZE = 1048;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MyPdfWriter myPdfWriter = new MyPdfWriter();
        Long id = (Long) req.getSession().getAttribute("user_id");
        System.out.println("before create = " + id);
        myPdfWriter.createPdf(id);

        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=table" + id + ".pdf");
        System.out.println("try take file");
        try (InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/pdf/table" + id + ".pdf");
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
