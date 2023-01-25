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
    private ResultRepo resultRepo;

    private AnswerService answerService;

    public ResultService(ResultRepo resultRepo) {
        this.answerService = new AnswerService(new AnswerRepo(), new ValidatorService());
        this.resultRepo = resultRepo;
    }

    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        return resultRepo.addResult(userId, testId, grade);
    }

    public List<ResultDto> getResultByUser(Long userId) throws DataBaseException {
        return resultRepo.resultDtoList(userId);
    }

    private List<Answer> convertUserResult(String[] userAnswer){
        List<Answer> userResult = new ArrayList<>();
        Answer answer = null;
        for (int i = 0; i < userAnswer.length; i++) {
            if (userAnswer[i].equals("on")) {
                answer.setResult(true);
                userResult.add(answer);
            } else if (userAnswer[i].equals("off")) {
                answer.setResult(false);
                userResult.add(answer);
            } else {
                answer = new Answer();
                answer.setId(Long.parseLong(userAnswer[i]));
            }
        }
        return userResult;
    }

    public boolean getResultByQuestion(Long questionId, String[] userAnswer) throws DataBaseException {
        boolean result = true;
        if (userAnswer == null) {
            return result;
        } else {
            List<Answer> checkingAnswer = convertUserResult(userAnswer);
            List<Answer> trueAnswers = answerService.getAnswers(questionId);
            System.out.println(checkingAnswer);
            System.out.println(trueAnswers);



            for (int i = 0; i < checkingAnswer.size(); i++) {
              //  if(checkingAnswer.get(i).getId()==trueAnswers.get(i).getId())





                boolean contain = trueAnswers.contains(checkingAnswer.get(i));
                if (contain == false) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

//    private List<Answer> getUserAnswer(String[] res) {
//        Answer answer;
//        List<Answer> userAnswers = new ArrayList<>();
//        int count = -1;
//        for (int i = 0; i < res.length; i++) {
//            if (!res[i].equals("on")) {
//                answer = new Answer();
//                answer.setId(Long.parseLong(res[i]));
//                answer.setResult(false);
//                userAnswers.add(answer);
//                count++;
//            } else {
//                userAnswers.get(count).setResult(true);
//            }
//        }
//        return userAnswers;
//    }


    public Integer getGrade(List<Boolean> userAnswer, Integer countQuestion) {
        long count = userAnswer.stream().filter(bool -> bool.equals(true)).count();
        return Math.toIntExact(count * 100 / countQuestion);
    }
}
