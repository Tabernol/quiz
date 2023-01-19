package servises;

import dto.ResultDto;
import exeptions.DataBaseException;
import models.Answer;
import repo.AnswerRepo;
import repo.ResultRepo;
import repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class ResultService {
    ResultRepo resultRepo;

    AnswerService answerService = new AnswerService(new AnswerRepo());

    public ResultService(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        return resultRepo.addResult(userId, testId, grade);
    }

    public List<ResultDto> getResultByUser(Long userId) throws DataBaseException {
        return resultRepo.resultDtoList(userId);
    }

    public boolean getResultByQuestion(Long questionId, String[] userAnswer) throws DataBaseException {
        boolean result = true;
        if (userAnswer == null) {
            return result;
        } else {
            List<Answer> checkingAnswer = getUserAnswer(userAnswer);
            List<Answer> trueAnswers = answerService.getAnswers(questionId);
            for (int i = 0; i < checkingAnswer.size(); i++) {
                boolean contain = trueAnswers.contains(checkingAnswer.get(i));
                if (contain == false) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    private List<Answer> getUserAnswer(String[] res) {
        Answer answer;
        List<Answer> userAnswers = new ArrayList<>();
        int count = -1;
        for (int i = 0; i < res.length; i++) {
            if (!res[i].equals("on")) {
                answer = new Answer();
                answer.setId(Long.parseLong(res[i]));
                answer.setResult(false);
                userAnswers.add(answer);
                count++;
            } else {
                userAnswers.get(count).setResult(true);
            }
        }
        return userAnswers;
    }


    public Integer getGrade(List<Boolean> userAnswer, Integer countQuestion){
        long count = userAnswer.stream().filter(bool -> bool.equals(true)).count();
       return Math.toIntExact(count * 100 / countQuestion);
    }
}
