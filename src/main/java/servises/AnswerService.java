package servises;

import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.Answer;
import repo.impl.AnswerRepoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call Answer Repo.class or throw an exception
 */
@Slf4j
public class AnswerService implements
        ConvertToDtoAble<AnswerDto, Answer>,
        ConvertToEntityAble<Answer, AnswerDto> {
    /**
     * Class contains:
     * answerRepo field for work with AnswerRepo.class
     * validatorService field for validate input date from other
     */
    private AnswerRepoImpl answerRepoImpl;
    private ValidatorService validatorService;


    public AnswerService(AnswerRepoImpl answerRepoImpl, ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.answerRepoImpl = answerRepoImpl;
    }

    /**
     * method call repository layer and received list of Answer
     *
     * @param questionId is unique number Question in database
     * @return list of Answer by questionId
     * @throws DataBaseException
     */
    public Set<AnswerDto> getAnswers(Long questionId) throws DataBaseException {
        log.info("SERVICE ANSWER get answers by  question {}", questionId);
        Set<AnswerDto> answerDtoSet = new HashSet<>();
        for (Answer answer : answerRepoImpl.getAnswersByQuestionId(questionId)) {
            answerDtoSet.add(mapToDto(answer));
        }
        return answerDtoSet;
    }

    /**
     * Mhe method takes input, validates it, and calls to repository layer to create Answer
     *
     * @param questionId is unique number Question in database
     * @param text       is new text of answer for question by id
     * @param result     can be true or false, and define the result
     * @return 1 if Answer has created
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int createAnswer(AnswerDto answerDto) throws DataBaseException, ValidateException {
        validatorService.validateText(answerDto.getText());
        log.info("SERVICE ANSWER creating new answer");
        return answerRepoImpl.create(mapToEntity(answerDto));
    }

    /**
     * The method takes questionId, validates it, and calls to repository layer to delete answer
     *
     * @param id is unique number Answer in database
     * @return 1 if Answer has deleted
     * @throws DataBaseException
     */
    public int deleteAnswer(Long id) throws DataBaseException {
        log.info("SERVICE ANSWER delete answer " + id);
        return answerRepoImpl.delete(id);
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
      //  answer.setId(answerDto.getId());
        answer.setText(answerDto.getText());
        answer.setQuestionId(answerDto.getQuestionId());
        answer.setResult(answerDto.getResult());
        return answer;
    }
}
