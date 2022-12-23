package servises;

import dao.impl.TestDao;
import models.Test;
import repo.TestRepo;

import java.util.List;

public class TestService {
    TestDao testDao = new TestDao();
    TestRepo testRepo = new TestRepo();

    public List<Test> getAll() {
        return testDao.getAll();
    }

    public List<Test> getAllByAZ() {
        return testRepo.getAllByAZ();
    }

    public void createTest(String name, Long subjectId, int difficult, int duration) {
        testDao.createTest(name, subjectId, difficult, duration);
    }
}
