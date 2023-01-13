import com.zaxxer.hikari.HikariDataSource;
import connection.MyDataSource;
import models.Answer;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repo.AnswerRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class AnswerRepoTest {
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;

    AnswerRepo answerRepo = new AnswerRepo();


    @BeforeEach
    public void setUp() {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        // MyDataSource.init();
    }

//    @Test
//    public void testMockDBConnection() throws Exception {
//        List<Answer> answerList = new ArrayList<>();
//        Mockito.when(mockConnection.prepareStatement(Mockito.any())).thenReturn(mockPreparedStatement);
//        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//
//        int answer = answerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean());
//       // List<Answer> answersByQuestionId = answerRepo.getAnswersByQuestionId(Mockito.anyLong());
//
////        Assertions.assertEquals(1, answer);
//
//    }
}
//    @BeforeEach
//    void setUp() {
//        mockAnswerRepo = Mockito.mock(AnswerRepo.class);
//        answerService = new AnswerService(mockAnswerRepo);
//    }
//    @Mock
//    AnswerRepo mockAnswerRepo;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

//    @Test
//    public void testCreateAnswer() throws Exception {
//        int answer1;
//        int answer2;
//        Mockito.when(mockAnswerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean()))
//                .thenReturn(1);
//        ;
//        answer2 = mockAnswerRepo.createAnswer(11L, "answer2", false);
////        Assertions.assertEquals(1,mockAnswerRepo.createAnswer(Mockito.anyLong(),
////                Mockito.anyString(), Mockito.anyBoolean()));
//        Assertions.assertEquals(1, answer2);
//    }


//    public void getAllAnswer() {
//        Mockito.when(mockConnection.)
//
//
//    }

