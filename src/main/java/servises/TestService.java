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


    public void createTest(String name, String subject, int difficult, int duration) {
        testDao.createTest(name, subject, difficult, duration);
    }

    public List<String> getDistinctSubjects() {
        return testRepo.getDistinctSubject();
    }

    public List<Test> getFilterTests(String subject, String order){
        return testRepo.getFilterTest(subject, order);
    }

    public void delete(Long id){
        testDao.delete(id);
    }

    public Test get(Long id){
        return testDao.get(id);
    }

    public void update(Long id, String name, String subject, int difficult, int duration){
        testRepo.updateInfoTest(id, name, subject, difficult, duration);
    }
}
