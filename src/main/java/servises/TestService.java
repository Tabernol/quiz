package servises;

import dao.impl.TestDao;
import models.Test;
import repo.TestRepo;

import java.util.List;

public class TestService {
    TestDao testDao = new TestDao();
    TestRepo testRepo = new TestRepo();

    public List<Test> getAll(String order, int rows) {
        return testRepo.getAll(order, rows);
    }

    public List<Test> getAll() {
        return testDao.getAll();
    }


    public void createTest(String name, String subject, int difficult, int duration) {
        testDao.createTest(name, subject, difficult, duration);
    }

    public List<String> getDistinctSubjects() {
        return testRepo.getDistinctSubject();
    }

    public List<Test> getFilterTests(String subject, String order, int rows) {
        if (subject.equals("all")) {
            return getAll(order, rows);
        } else {
            return testRepo.getFilterTest(subject, order, rows);
        }
    }

    public void delete(Long id) {
        testDao.delete(id);
    }

    public Test get(Long id) {
        return testDao.get(id);
    }

    public void update(Long id, String name, String subject, int difficult, int duration) {
        testRepo.updateInfoTest(id, name, subject, difficult, duration);
    }

    public List<Test> getSortOrder(String order) {
        return testRepo.getSortOrder(order);
    }

    public int countPages(String subject, Integer rowsOnPage) {
        Integer count = 0;
        if(subject.equals("all")){
            count = testRepo.getCount();
        } else {
            count = testRepo.getCount(subject);
        }

        if (count % rowsOnPage != 0) {
            return count / rowsOnPage + 1;
        }
        return count/ rowsOnPage;
    }

    public List<Test> nextPage(String subject, String order, Integer rows, Integer numberOfPage){
      return   testRepo.nextPage(subject, order, rows, numberOfPage);
    }



}
