package command.post;

import command.get.FilterTests;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.TestRepo;
import servises.TestService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(DeleteTest.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {

        TestService testService = new TestService(new TestRepo(), new ValidatorService());
        Long id = Long.valueOf(req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));

        try {
            testService.delete(id);
            logger.info("Test with id " + id + "has deleted");
            FilterTests filterTests = new FilterTests();
            filterTests.execute(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Test with id " + id + "has not deleted", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);

        }


    }
}
