package servises;

import exeptions.DataBaseException;
import models.Test;
import repo.TestRepo;

import java.util.List;

public class TestService {
    TestRepo testRepo = new TestRepo();


    public List<Test> getAll() throws DataBaseException {
        return testRepo.getAll();
    }


    public int createTest(String name, String subject, int difficult, int duration) throws DataBaseException {
        return testRepo.createTest(name, subject, difficult, duration);
    }

    public List<String> getDistinctSubjects() throws DataBaseException {
        return testRepo.getDistinctSubject();
    }

    public List<Test> getFilterTests(String subject, String order, int rows) throws DataBaseException {
        if (subject.equals("all")) {
            return testRepo.getAll(order, rows);
        } else {
            return testRepo.getFilterTest(subject, order, rows);
        }
    }

    public void delete(Long id) throws DataBaseException {
        testRepo.delete(id);
    }

    public Test get(Long id) throws DataBaseException {
        return testRepo.get(id);
    }

    public int update(Long id, String name, String subject, int difficult, int duration) throws DataBaseException {
        return testRepo.updateInfoTest(id, name, subject, difficult, duration);
    }


    public int countPages(String subject, Integer rowsOnPage) throws DataBaseException {
        Integer count = 0;
        if (subject.equals("all")) {
            count = testRepo.getCount();
        } else {
            count = testRepo.getCount(subject);
        }

        if (count % rowsOnPage != 0) {
            return count / rowsOnPage + 1;
        }
        return count / rowsOnPage;
    }

//    public List<Test> nextPage(String subject, String order, Integer rows, Integer numberOfPage) {
//        return testRepo.nextPage(subject, order, rows, numberOfPage);
//    }
//
//    public List<Test> nextPage(String order, Integer rows, Integer numberOfPage) {
//        return testRepo.nextPage(order, rows, numberOfPage);
//    }

    public void addPointPopularity(Long idTest) throws DataBaseException {
        testRepo.addPopularity(idTest);
    }

    public boolean isNameExist(String name) throws DataBaseException {
        return testRepo.isNameExist(name);
    }


}
