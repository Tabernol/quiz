package servises.impl;

import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.Answer;
import repo.AnswerRepo;
import servises.AnswerService;
import util.converter.ConvertToDtoAble;
import util.converter.ConvertToEntityAble;
import servises.ValidatorService;

import java.util.HashSet;
import java.util.Set;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call Answer Repo.class or throw an exception
 */
@Slf4j
public class AnswerServiceImpl implements AnswerService,
        ConvertToDtoAble<AnswerDto, Answer>,
        ConvertToEntityAble<Answer, AnswerDto> {
    /**
     * Class contains:
     * answerRepo field for work with AnswerRepo.class
     * validatorService field for validate input date from other
     */
    private final AnswerRepo answerRepo;
    private final ValidatorService validatorService;


    public AnswerServiceImpl(AnswerRepo answerRepo, ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.answerRepo = answerRepo;
    }

    /**
     * method call repository layer and received list of Answer
     *
     * @param questionId is unique number Question in database
     * @return list of Answer by questionId
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Set<AnswerDto> getAnswers(Long questionId) throws DataBaseException {
        log.info("SERVICE ANSWER get answers by  question {}", questionId);
        Set<AnswerDto> answerDtoSet = new HashSet<>();
        for (Answer answer : answerRepo.getAnswersByQuestionId(questionId)) {
            answerDtoSet.add(mapToDto(answer));
        }
        return answerDtoSet;
    }



    /**
     * Mhe method takes input, validates it, and calls to repository layer to create Answer
     * @param answerDto contains information about answer
     * @return 1 if Answer has created
     * @throws DataBaseException is wrapper of SQLException
     * @throws ValidateException
     */
    @Override
    public long create(AnswerDto answerDto) throws DataBaseException, ValidateException {
        validatorService.validateText(answerDto.getText());
        log.info("SERVICE ANSWER creating new answer");
        return answerRepo.create(mapToEntity(answerDto));
    }

    /**
     * The method takes questionId, validates it, and calls to repository layer to delete answer
     *
     * @param id is unique number Answer in database
     * @return 1 if Answer has deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(Long id) throws DataBaseException {
        log.info("SERVICE ANSWER delete answer " + id);
        return answerRepo.delete(id);
    }

    @Override
    public int update(AnswerDto answerDto) throws DataBaseException, ValidateException {
        return 0;
    }

    @Override
    public AnswerDto get(Long id) throws DataBaseException {
        return null;
    }


    @Override
    public AnswerDto mapToDto(Answer entity) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(entity.getId());
        answerDto.setQuestionId(entity.getQuestionId());
        answerDto.setResult(entity.isResult());
        answerDto.setText(entity.getText());
        return answerDto;
    }

    @Override
    public Answer mapToEntity(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setText(answerDto.getText());
        answer.setQuestionId(answerDto.getQuestionId());
        answer.setResult(answerDto.isResult());
        return answer;
    }
}
