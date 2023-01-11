import models.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.AnswerRepo;
import servises.AnswerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerRepoTest {
    private AnswerService answerService;

    // Mock
    private AnswerRepo mockAnswerRepo;

    @BeforeEach
    void setUp() {
        mockAnswerRepo = Mockito.mock(AnswerRepo.class);
        answerService = new AnswerService(mockAnswerRepo);
    }
//    @Mock
//    AnswerRepo mockAnswerRepo;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testCreateAnswer() throws Exception {
        int answer1;
        int answer2;
        Mockito.when(mockAnswerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(1);
        ;
        answer2 = mockAnswerRepo.createAnswer(11L, "answer2", false);
//        Assertions.assertEquals(1,mockAnswerRepo.createAnswer(Mockito.anyLong(),
//                Mockito.anyString(), Mockito.anyBoolean()));
        Assertions.assertEquals(1, answer2);
    }

    public void deleteAnswerTest() {

    }


    public void getAllAnswer() {
        Answer answer = new Answer();
        answer.setId(1);
        answer.setResult(true);
        answer.setQuestionId(1);
        answer.setText("first");
        Answer answer2 = new Answer();
        answer2.setId(1);
        answer2.setResult(true);
        answer2.setQuestionId(1);
        answer2.setText("first");


    }


}
