import connection.MyDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import repo.AnswerRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.mockStatic;
@ExtendWith(MockitoExtension.class)
class AnswerRepoTest {
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    MockedStatic<MyDataSource> myDataSourceMockedStatic;
    @InjectMocks
    AnswerRepo answerRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        myDataSourceMockedStatic = mockStatic(MyDataSource.class);


        // MyDataSource.init();
    }
}

//    @Test
//    public void testMockDBConnection() throws Exception {
//
//        myDataSourceMockedStatic.when(MyDataSource::getConnection).thenReturn(mockConnection);
//
//
//        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
//
//        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        int answer = answerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean());
//
//        Assertions.assertEquals(1,answer);
//    }



//
//
//        Mockito.when(mockConnection.prepareStatement(Mockito.any())).thenReturn(mockPreparedStatement);
//        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        System.out.println("mock CON +++++++++++++++++++++++    " + mockConnection);
//        int answer = answerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean());
// List<Answer> answersByQuestionId = answerRepo.getAnswersByQuestionId(Mockito.anyLong());

//        Assertions.assertEquals(1, answer);


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
//
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

