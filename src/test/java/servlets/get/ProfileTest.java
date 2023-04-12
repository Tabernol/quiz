package servlets.get;

import command.get.Profile;
import dto.ResultDto;
import dto.UserDto;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.impl.ResultServiceImpl;
import servises.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileTest {
    Profile profile = new Profile();

    @Test
    public void profileTest() throws DataBaseException, ServletException, IOException {
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        final UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);

        final ResultServiceImpl resultService = Mockito.mock(ResultServiceImpl.class);

        UserDto userDto = new UserDto();
        List<ResultDto> resultDto = new ArrayList<>();
        userDto.setName("first");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestDispatcher("/WEB-INF/view/profile.jsp")).thenReturn(dispatcher);
        Mockito.when(session.getAttribute("user_id")).thenReturn(12L);
        Mockito.when(userService.get(Mockito.anyLong())).thenReturn(userDto);


        profile.execute(request,response);

        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("/WEB-INF/view/profile.jsp");
//        Mockito.verify(request, Mockito.times(1)).getSession();
        Mockito.verify(dispatcher).forward(request, response);

    }
}
