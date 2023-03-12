package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.TestRepo;
import util.MyTable;
import util.query.*;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call TestRepo.class or throw an exception
 */
public class TestService {
    private static final Logger logger = LogManager.getLogger(TestService.class);
    /**
     * Class contains:
     * testRepo field for work with TestRepo.class
     * validatorService field for validate input date from other
     */
    private TestRepo testRepo;
    private ValidatorService validatorService;

    public TestService(TestRepo testRepo, ValidatorService validatorService) {
        this.testRepo = testRepo;
        this.validatorService = validatorService;
    }

    /**
     * This method validates the input parameters.
     * it checks the test name for uniqueness.
     * it causes the insertion of new data
     *
     * @param name      is unique name of test
     * @param subject   is subject of test
     * @param difficult can be from 0 to 100
     * @param duration  can be less than 30
     * @return 1 if test has created
     * @throws ValidateException
     * @throws DataBaseException
     */
    public int createTest(String name, String subject, int difficult, int duration)
            throws ValidateException, DataBaseException {
        validatorService.checkFieldsTest(name, subject, difficult, duration);
        validatorService.isNameExist(testRepo.isNameExist(name));
        logger.info("SERVICE TEST create new test with name " + name);
        return testRepo.createTest(name, subject, difficult, duration);
    }

    /**
     * This method calls to TestRepo.class for received List unique name subject
     *
     * @return list of unique name subject in the 'test' table in the database
     * @throws DataBaseException
     */
    public List<String> getDistinctSubjects() throws DataBaseException {
        logger.info("SERVICE TEST get distinct subject");
        return testRepo.getDistinctSubject();
    }

    /**
     * his method return List of test(quiz) on each page
     *
     * @param subject is subject to testing and was selected during filtering
     * @param order   is order of test was selected during filtering
     * @param rows    is number of rows on one page was selected during filtering
     * @param page    is page of chosen tests
     * @param role    is role of user in session
     * @return List of test on current page
     * @throws DataBaseException
     */
    public List<Test> getPageTestList(String subject, String order, Integer rows, Integer page, String role)
            throws DataBaseException {
        QueryCreator queryCreator = new QueryBuilderForTest();
        String query = queryCreator.getSQL(new MyQuery(subject, order, rows, page, role));

//        QueryBuilderForTest queryBuilderForTest = new QueryBuilderForTest();
//        String query = queryBuilderForTest.getQuery(subject, order, rows, page, role);
        logger.info("SERVICE TEST get list of test with selected filter");
        return testRepo.nextPage(query);
    }

    /**
     * This method calls to testRepo.class to delete test(quiz)
     *
     * @param id is unique number of test in database
     * @return 1 if test(quiz) has deleted
     * @throws DataBaseException
     */
    public int delete(Long id) throws DataBaseException {
        logger.info("SERVICE TEST delete test with id " + id);
        return testRepo.delete(id);
    }

    /**
     * This method calls to TestRepo.class to get object of Test
     *
     * @param id is unique number of test in database
     * @return models.Test
     * @throws DataBaseException
     */
    public Test get(Long id) throws DataBaseException {
        logger.info("SERVICE TEST get test with id " + id);
        return testRepo.get(id);
    }

    /**
     * this method validate input data and calls to TestRepo.class to update new data
     *
     * @param id        is unique number of test in database
     * @param name      is unique name of test
     * @param subject   is subject of tests or 'all' by default
     * @param difficult can be from 0 to 100
     * @param duration  can be less than 30
     * @return 1 if test has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int update(Long id, String name, String subject, int difficult, int duration) throws DataBaseException, ValidateException {
        validatorService.checkFieldsTest(name, subject, difficult, duration);
        logger.info("SERVICE TEST update test with id " + id);
        return testRepo.updateInfoTest(id, name, subject, difficult, duration);
    }

    /**
     * this method counts the number of pages for the selected filter by subject and number of rows
     *
     * @param subject    is subject of test
     * @param rowsOnPage is number of rows on one page
     * @return count of page for this filter
     * @throws DataBaseException
     */
    public int countPages(String subject, Integer rowsOnPage) throws DataBaseException {
        Integer count = amountTests(subject);
        return count % rowsOnPage == 0 ? count / rowsOnPage : count / rowsOnPage + 1;
    }

    /**
     * this method counts the number of tests (test)
     * for the selected filter or for all
     *
     * @param subject is subject of tests or 'all' by default
     * @return
     * @throws DataBaseException
     */
    private int amountTests(String subject) throws DataBaseException {
        logger.info("SERVICE TEST count of test with selected subject");
        return subject.equals("all") ? testRepo.getCount() : testRepo.getCount(subject);
    }

    /**
     * This method calls to TestRepo.class for add one point popularity for test
     *
     * @param idTest is unique number of test in database
     * @return 1 if test has updated
     * @throws DataBaseException
     */
    public int addPointPopularity(Long idTest) throws DataBaseException {
        logger.info("SERVICE TEST  add point of popularity");
        return testRepo.addPopularity(idTest);
    }

    /**
     * this method calls for TestRepo.class for change status test(quiz) on opposite
     *
     * @param testId is unique number of test in database
     * @return 1 if test has updated
     * @throws DataBaseException
     */
    public int changeStatus(Long testId) throws DataBaseException {
        Test test = testRepo.get(testId);
        if (test.getStatus().equals(Test.Status.BLOCKED)) {
            logger.info("SERVICE TEST with id " + testId + " is " + Test.Status.FREE);
            return testRepo.changeStatus(testId, Test.Status.FREE);
        } else {
            logger.info("SERVICE TEST with id " + testId + " is " + Test.Status.BLOCKED);
            return testRepo.changeStatus(testId, Test.Status.BLOCKED);
        }
    }
}
