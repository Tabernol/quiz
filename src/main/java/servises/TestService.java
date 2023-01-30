package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Test;
import repo.TestRepo;
import util.MyTable;
import util.QBuilder;
import util.QueryBuilderForTest;
import util.QueryFactory;

import java.util.List;

public class TestService {
    private TestRepo testRepo;
    private ValidatorService validatorService;

    public TestService(TestRepo testRepo, ValidatorService validatorService) {
        this.testRepo = testRepo;
        this.validatorService = validatorService;
    }

//    public List<Test> getAll() throws DataBaseException {
//        return testRepo.getAll();
//    }

    public int createTest(String name, String subject, int difficult, int duration)
            throws ValidateException, DataBaseException {
      validatorService.checkFieldsTest(name,subject,difficult,duration);
      validatorService.isNameExist(testRepo.isNameExist(name));
        return testRepo.createTest(name, subject, difficult, duration);
    }

    public List<String> getDistinctSubjects() throws DataBaseException {
        return testRepo.getDistinctSubject();
    }

    public List<Test> getPageTestList(String subject, String order, Integer rows, Integer page) throws DataBaseException {
        QueryFactory queryFactory = new QueryFactory();
        QBuilder qBuilder = (QueryBuilderForTest) queryFactory.getQueryBuilder(MyTable.TEST);
        qBuilder.setFilter(subject);
        qBuilder.setOrderBy(order);
        qBuilder.setLimit(rows);
        qBuilder.setOffSet(page);
        String query = qBuilder.getQuery();
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
        validatorService.checkFieldsTest(name,subject,difficult,duration);
        return testRepo.updateInfoTest(id, name, subject, difficult, duration);
    }


    public int countPages(String subject, Integer rowsOnPage) throws DataBaseException {
        Integer count = amountTests(subject);
        return count % rowsOnPage == 0 ? count / rowsOnPage : count / rowsOnPage + 1;
    }

    private int amountTests(String subject) throws DataBaseException {
        return subject.equals("all") ? testRepo.getCount() : testRepo.getCount(subject);
    }


//    public List<Test> nextPage(String subject, String order, Integer rows, Integer numberOfPage) {
//        return testRepo.nextPage(subject, order, rows, numberOfPage);
//    }
//
//    public List<Test> nextPage(String order, Integer rows, Integer numberOfPage) {
//        return testRepo.nextPage(order, rows, numberOfPage);
//    }

    public int addPointPopularity(Long idTest) throws DataBaseException {
        return testRepo.addPopularity(idTest);
    }

//    public boolean isNameExist(String name) throws DataBaseException {
//        return testRepo.isNameExist(name);
//    }
}
