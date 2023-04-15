package servises.impl;

import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.Test;
import repo.TestRepo;
import util.converter.ConvertToDtoAble;
import util.converter.ConvertToEntityAble;
import servises.TestService;
import servises.ValidatorService;
import util.query.*;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call TestRepo.class or throw an exception
 */
@Slf4j
public class TestServiceImpl implements TestService,
        ConvertToEntityAble<Test, TestDto>,
        ConvertToDtoAble<TestDto, Test> {
    /**
     * Class contains:
     * testRepo field for work with TestRepo.class
     * validatorService field for validate input date from other
     */
    private final TestRepo testRepo;
    private final ValidatorService validatorService;

    public TestServiceImpl(TestRepo testRepo, ValidatorService validatorService) {
        this.testRepo = testRepo;
        this.validatorService = validatorService;
    }

    /**
     * This method validates the input parameters.
     * it checks the test name for uniqueness.
     * it causes the insertion of new data
     *
     * @param testDto The data transfer object containing information about the test
     * @return 1 if test has created
     * @throws ValidateException
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public long create(TestDto testDto)
            throws ValidateException, DataBaseException {
        validatorService.checkFieldsTest(testDto);
        validatorService.isNameExist(testRepo.isNameExist(testDto.getName()));
        log.info("SERVICE TEST create new test with name {}", testDto);
        return testRepo.create(mapToEntity(testDto));
    }

    /**
     * This method calls to TestRepo.class for received List unique name subject
     *
     * @return list of unique name subject in the 'test' table in the database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<String> getDistinctSubjects() throws DataBaseException {
        log.info("SERVICE TEST get distinct subject");
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
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<Test> getPageTestList(String subject, String order, Integer rows, Integer page, String role)
            throws DataBaseException {
        QueryCreator queryCreator = new QueryBuilderForTest();
        String query = queryCreator.getSQL(new MyQuery(subject, order, rows, page, role));
        log.info("SERVICE TEST get list of test with selected filter");
        return testRepo.nextPage(query);
    }

    /**
     * This method calls to testRepo.class to delete test(quiz)
     *
     * @param id is unique number of test in database
     * @return 1 if test(quiz) has deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(Long id) throws DataBaseException {
        log.info("SERVICE TEST delete test with id " + id);
        return testRepo.delete(id);
    }

    /**
     * This method calls to TestRepo.class to get object of Test
     *
     * @param id is unique number of test in database
     * @return models.Test
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public TestDto get(Long id) throws DataBaseException {
        log.info("SERVICE TEST get test with id " + id);
        return mapToDto(testRepo.get(id));
    }

    /**
     * this method validate input data and calls to TestRepo.class to update new data
     *
     * @param testDto The data transfer object containing information about the test
     * @return 1 if test has updated
     * @throws DataBaseException is wrapper of SQLException
     * @throws ValidateException
     */
    @Override
    public int update(TestDto testDto) throws DataBaseException, ValidateException {
        validatorService.checkFieldsTest(testDto);
        log.info("SERVICE TEST update test {}", testDto);
        return testRepo.update(mapToEntity(testDto));
    }

    /**
     * this method counts the number of pages for the selected filter by subject and number of rows
     *
     * @param subject    is subject of test
     * @param rowsOnPage is number of rows on one page
     * @return count of page for this filter
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
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
     * @throws DataBaseException is wrapper of SQLException
     */
    private int amountTests(String subject) throws DataBaseException {
        log.info("SERVICE TEST count of test with selected subject");
        return subject.equals("all") ? testRepo.getCount() : testRepo.getCount(subject);
    }

    /**
     * This method calls to TestRepo.class for add one point popularity for test
     *
     * @param idTest is unique number of test in database
     * @return 1 if test has updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int addPointPopularity(Long idTest) throws DataBaseException {
        log.info("SERVICE TEST  add point of popularity");
        return testRepo.addPopularity(idTest);
    }

    /**
     * this method calls for TestRepo.class for change status test(quiz) on opposite
     *
     * @param testId is unique number of test in database
     * @return 1 if test has updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int changeStatus(Long testId) throws DataBaseException {
        Test test = testRepo.get(testId);
        if (test.getStatus().equals(Test.Status.BLOCKED)) {
            log.info("SERVICE TEST with id " + testId + " is " + Test.Status.FREE);
            return testRepo.changeStatus(testId, Test.Status.FREE);
        } else {
            log.info("SERVICE TEST with id " + testId + " is " + Test.Status.BLOCKED);
            return testRepo.changeStatus(testId, Test.Status.BLOCKED);
        }
    }

    @Override
    public Test mapToEntity(TestDto testDto) {
        return Test.builder().id(testDto.getId())
                .name(testDto.getName())
                .subject(testDto.getSubject())
                .difficult(testDto.getDifficult())
                .duration(testDto.getDuration())
                .popularity(testDto.getPopularity())
                .build();
    }

    @Override
    public TestDto mapToDto(Test entity) {
        return TestDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .difficult(entity.getDifficult())
                .duration(entity.getDuration())
                .subject(entity.getSubject())
                .popularity(entity.getPopularity())
                .status(entity.getStatus().getStatus())
                .build();
    }
}
