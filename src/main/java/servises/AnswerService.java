package servises;

import models.Answer;
import repo.AnswerRepo;

import java.util.List;

public class AnswerService {
    AnswerRepo answerRepo = new AnswerRepo();

    public List<Answer> getAnswers(Long questionId) {
        return answerRepo.getAnswersByQuestionId(questionId);
    }

    public void createAnswer(Long questionId, String text, boolean result) {
        answerRepo.createAnswer(questionId, text, result);
    }

    public void deleteAnswer(Long id) {
        answerRepo.delete(id);
    }

    public List<String> getOnlyOptionAnswers(Long idQuestion) {
        return answerRepo.getOptionsAnswerForQuestion(idQuestion);
    }

}
