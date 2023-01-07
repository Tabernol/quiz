package servises;

import exeptions.DataBaseException;
import models.Test;
import repo.TestRepo;

import java.util.List;

public class PaginationService {
    TestRepo testRepo = new TestRepo();

    public List<Test> nextPage(String subject, String order, Integer rows, Integer numberOfPage) throws DataBaseException {
        return testRepo.nextPage(subject, order, rows, numberOfPage);
    }

    public List<Test> nextPage(String order, Integer rows, Integer numberOfPage) throws DataBaseException {
        return testRepo.nextPage(order, rows, numberOfPage);
    }


}
