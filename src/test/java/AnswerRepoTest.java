import connection.MyDataSource;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repo.AnswerRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class AnswerRepoTest {
//    @InjectMocks
//    MyDataSource mockMyDataSource;
//    @Mock
//    Connection mockConnection;
//    @Mock
//    PreparedStatement mockPreparedStatement;
}


//    @BeforeAll
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }



//    @Test
//    public void testMockDBConnection() throws Exception {
////        mockConnection = Mockito.mock(Connection.class);
////        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
////
//        Mockito.when(MyDataSource.getConnection()).thenReturn(mockConnection);
//        Mockito.when(mockConnection.prepareStatement(Mockito.any())).thenReturn(mockPreparedStatement);
//        Mockito.when(mockConnection.prepareStatement(Mockito.any()).executeUpdate(Mockito.any())).thenReturn(1);
//
//        int answer = answerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean());
//
//        System.out.println(answer);
//
//        // int first = answerRepo.createAnswer(1L, "first", true);
//        Assertions.assertEquals(1, answer);
        // Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
//    }


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

