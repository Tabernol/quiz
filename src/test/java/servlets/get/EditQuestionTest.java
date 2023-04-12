package servlets.get;

import command.get.EditQuestion;
import dto.AnswerDto;
import dto.QuestionDto;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.impl.AnswerServiceImpl;
import servises.impl.QuestionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EditQuestionTest {
    EditQuestion editQuestion = new EditQuestion();

    @Test
    public void editQuestionTest() throws DataBaseException, ServletException, IOException {
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        QuestionServiceImpl mockQuestionService = Mockito.mock(QuestionServiceImpl.class);
        AnswerServiceImpl mockAnswerService = Mockito.mock(AnswerServiceImpl.class);


        QuestionDto questionDto = new QuestionDto();
        Set<AnswerDto> answerList = new HashSet<>();
        Mockito.when(request.getParameter("test_id")).thenReturn("12");
        Mockito.when(request.getParameter("question_id")).thenReturn("44");

        Mockito.when(mockQuestionService.get(Mockito.anyLong())).thenReturn(questionDto);
        Mockito.when(mockAnswerService.getAnswers(Mockito.anyLong())).thenReturn(answerList);

        Mockito.when(request.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp")).thenReturn(dispatcher);

        editQuestion.execute(request, response);
        Mockito.verify(request, Mockito.times(1))
                .getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp");
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request, response);
    }
}
