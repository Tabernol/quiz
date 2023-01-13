package servlets.post;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteAnswerTest {

    @Test
    public void deleteAnswer(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("test_id")).thenReturn("1");
        when(request.getParameter("question_id")).thenReturn("2");
        when(request.getParameter("answer_id")).thenReturn("3");
        assertEquals(1L, Long.valueOf(request.getParameter("test_id")));
        assertEquals(2L, Long.valueOf(request.getParameter("question_id")));
        assertEquals(3L, Long.valueOf(request.getParameter("answer_id")));

    }
    }

