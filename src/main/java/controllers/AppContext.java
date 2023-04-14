package controllers;

import connection.MyDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import repo.*;
import repo.impl.*;
import servises.*;
import servises.impl.*;
import util.pdfWriter.MyPdfWriter;
import validator.DataValidator;

import javax.sql.DataSource;

@Getter
@Slf4j
public class AppContext {
    private static AppContext appContext;

    private final DataSource dataSource;
    //=============================
    private final AnswerRepo answerRepo;
    private final QuestionRepo questionRepo;
    private final TestRepo testRepo;
    private final ImageRepo imageRepo;
    private final ResultRepo resultRepo;
    private final UserRepo userRepo;
    //=======================
    private final DataValidator dataValidator;
    //================================
    private final ValidatorService validatorService;
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final TestService testService;
    private final ResultService resultService;
    private final ImageService imageService;
    private final UserService userService;
    //=================================
    private final MyPdfWriter myPdfWriter;


    private AppContext() {
        this.dataSource = MyDataSource.getInstance();
        this.answerRepo = new AnswerRepoImpl(dataSource);
        this.questionRepo = new QuestionRepoImpl(dataSource);
        this.testRepo = new TestRepoImpl(dataSource);
        this.imageRepo = new ImageRepoImpl(dataSource);
        this.resultRepo = new ResultRepoImpl(dataSource);
        this.userRepo = new UserRepoImpl(dataSource);

        this.dataValidator = new DataValidator();

        this.validatorService = new ValidatorServiceImpl(dataValidator);

        this.answerService = new AnswerServiceImpl(answerRepo, validatorService);
        this.questionService = new QuestionServiceImpl(questionRepo, validatorService);
        this.testService = new TestServiceImpl(testRepo, validatorService);
        this.resultService = new ResultServiceImpl(resultRepo, answerService);
        this.imageService = new ImageServiceImpl(imageRepo);
        this.userService = new UserServiceImpl(userRepo, validatorService);

        this.myPdfWriter = new MyPdfWriter(resultService);

    }

    public static AppContext getInstance() {
        if (appContext == null) {
            appContext = new AppContext();
            log.info("Context initialized");
        }
        log.info("Get Context");
        return appContext;
    }
}
