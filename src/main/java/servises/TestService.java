package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Test;
import repo.TestRepo;
import util.MyTable;
import util.query.*;

import java.util.List;

public class TestService {
    private TestRepo testRepo;
    private ValidatorService validatorService;

    public TestService(TestRepo testRepo, ValidatorService validatorService) {
        this.testRepo = testRepo;
        this.validatorService = validatorService;
    }


    public int createTest(String name, String subject, int difficult, int duration)
            throws ValidateException, DataBaseException {
        validatorService.checkFieldsTest(name, subject, difficult, duration);
        validatorService.isNameExist(testRepo.isNameExist(name));
        return testRepo.createTest(name, subject, difficult, duration);
    }

    public List<String> getDistinctSubjects() throws DataBaseException {
        return testRepo.getDistinctSubject();
    }

    public List<Test> getPageTestList(String subject, String order, Integer rows, Integer page, String role) throws DataBaseException {
        //     QueryFactory queryFactory = new QueryFactory();
        QueryBuilderForTest queryBuilderForTest = new QueryBuilderForTest();
        String query = queryBuilderForTest.getQuery(subject, order, rows, page, role);
        System.out.println("QUERY = " + query);
        return testRepo.nextPage(query);
    }

    public int delete(Long id) throws DataBaseException {
        return testRepo.delete(id);
    }

    public Test get(Long id) throws DataBaseException {
        return testRepo.get(id);
    }

    public int update(Long id, String name, String subject, int difficult, int duration) throws DataBaseException, ValidateException {
        validatorService.checkFieldsTest(name, subject, difficult, duration);
        //check by validator status
        return testRepo.updateInfoTest(id, name, subject, difficult, duration);
    }


    public int countPages(String subject, Integer rowsOnPage) throws DataBaseException {
        Integer count = amountTests(subject);
        return count % rowsOnPage == 0 ? count / rowsOnPage : count / rowsOnPage + 1;
    }

    private int amountTests(String subject) throws DataBaseException {
        return subject.equals("all") ? testRepo.getCount() : testRepo.getCount(subject);
    }

    public int addPointPopularity(Long idTest) throws DataBaseException {
        return testRepo.addPopularity(idTest);
    }

    public int changeStatus(Long testId) throws DataBaseException {
        Test test = testRepo.get(testId);
        if (test.getStatus().equals(Test.Status.BLOCKED)) {
            return testRepo.changeStatus(testId, Test.Status.FREE);
        } else {
            return testRepo.changeStatus(testId, Test.Status.BLOCKED);
        }
    }
}
