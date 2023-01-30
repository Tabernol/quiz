package servises;

import dto.ResultDto;
import exeptions.DataBaseException;
import models.Answer;
import repo.AnswerRepo;
import repo.ResultRepo;
import repo.UserRepo;
import util.MyTable;
import util.QBuilder;
import util.QueryBuilderForResult;
import util.QueryFactory;

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

    private List<Answer> convertUserResult(String[] userAnswer) {
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
            System.out.println("user answers  " + checkingAnswer);
            System.out.println("true answers " + trueAnswers);

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

    public Integer getCountResultByUser(Long user_id, String sub) throws DataBaseException {
        if (sub.equals("all")) {
            return resultRepo.getCountResultByUser(user_id);
        } else {
            return resultRepo.getCountResultByUserAndSubject(user_id, sub);
        }

    }

    public Integer getCountPagesResult(Long userId, Integer row, String sub) throws DataBaseException {
        Integer passedTest = getCountResultByUser(userId, sub);
        return passedTest % row == 0 ? passedTest / row : (passedTest / row) + 1;

    }

    public List<ResultDto> getPageResultList(Long idUser, String sub, String order, Integer limit, Integer offSet) throws DataBaseException {
        QueryFactory queryFactory = new QueryFactory();
        QueryBuilderForResult queryBuilder = (QueryBuilderForResult) queryFactory.getQueryBuilder(MyTable.RESULT);
        queryBuilder.setFilter(idUser.toString());
        queryBuilder.setOrderBy(order);
        queryBuilder.setLimit(limit);
        queryBuilder.setOffSet(offSet);
        queryBuilder.setAndSubject(sub);

        String query = queryBuilder.getQuery();
        System.out.println("QUERY = " + query);
        return resultRepo.getPageResultList(query);
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
