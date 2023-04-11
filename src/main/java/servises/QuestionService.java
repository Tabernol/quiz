package servises;

import dto.QuestionDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.Question;

import repo.impl.QuestionRepoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call QuestionRepo.class or throw an exception
 */
@Slf4j
public class QuestionService implements
        ConvertToDtoAble<QuestionDto, Question>,
        ConvertToEntityAble<Question, QuestionDto> {
    /**
     * Class contains:
     * questionRepo field for work with QuestionRepo.class
     * validatorService field for validate input date from other
     */
    private QuestionRepoImpl questionRepoImpl;
    private ValidatorService validatorService;

    public QuestionService(QuestionRepoImpl questionRepoImpl, ValidatorService validatorService) {
        this.questionRepoImpl = questionRepoImpl;
        this.validatorService = validatorService;
    }

    /**
     * The method takes input questionId and calls to repository layer
     *
     * @param id is unique number Test is database
     * @return List of Question by TestId
     * @throws DataBaseException
     */
    public List<QuestionDto> getAllById(Long id) throws DataBaseException {
        log.info("SERVICE QUESTION get all");
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionRepoImpl.getAllById(id)) {
            questionDtoList.add(mapToDto(question));
        }
        return questionDtoList;
    }

    /**
     * The method takes input, validates it, and calls the repository layer to create a new question
     *
     * @param questionDto The data transfer object containing information about the question.
     * @return 1 if question has added
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int addQuestion(QuestionDto questionDto) throws DataBaseException, ValidateException {
        validatorService.validateText(questionDto.getText());
        Question question = mapToEntity(questionDto);
        log.info("SERVICE QUESTION add question for test {}", question);
        return questionRepoImpl.create(question);
    }

    /**
     * The method takes questionId, and calls the repository layer to delete question
     *
     * @param id is unique number Question in database
     * @return 1 if question has deleted
     * @throws DataBaseException
     */
    public int deleteQuestion(Long id) throws DataBaseException {
        log.info("SERVICE QUESTION deleting question with id " + id);
        return questionRepoImpl.delete(id);
    }

    /**
     * The method takes questionId, and calls the repository layer to get question by id
     *
     * @param id s unique number Question in database
     * @return Question by id
     * @throws DataBaseException
     */
    public QuestionDto get(Long id) throws DataBaseException {
        log.info("SERVICE QUESTION get question with id " + id);
        return mapToDto(questionRepoImpl.get(id));
    }

    /**
     * The method takes input, validates it, and calls the repository layer to update this question
     *
     * @param questionDto The data transfer object containing information about the question.
     * @return 1 if question has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int update(QuestionDto questionDto) throws DataBaseException, ValidateException {
        validatorService.validateText(questionDto.getText());
        log.info("SERVICE QUESTION update question {}", questionDto);
        return questionRepoImpl.update(mapToEntity(questionDto));
    }

    /**
     * This method calls Question Repo.class to update the URL in the specific question identified by questionId
     *
     * @param url is new URL
     * @param id  is unique number Question in database
     * @return 1 if question has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int updateImage(String url, Long id) throws DataBaseException, ValidateException {
        if (url.equals("")) {
            url = null;
        }
        log.info("SERVICE QUESTION update url image for question with id " + id);
        return questionRepoImpl.updateImageQuestion(url, id);
    }

    @Override
    public QuestionDto mapToDto(Question entity) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(entity.getId());
        questionDto.setText(entity.getText());
        questionDto.setTestId(entity.getTestId());
        questionDto.setUrlImage(entity.getUrlImage());
        return questionDto;
    }

    @Override
    public Question mapToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setTestId(questionDto.getTestId());
        question.setText(questionDto.getText());
        question.setUrlImage(questionDto.getUrlImage());
        return question;
    }
}
