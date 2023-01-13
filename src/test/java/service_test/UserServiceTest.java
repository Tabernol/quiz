package service_test;

import exeptions.DataBaseException;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.TestRepo;
import repo.UserRepo;
import servises.TestService;
import servises.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    @Mock
    UserRepo mockUserRepo;
    UserService userService;

    @BeforeEach
    public void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        userService = new UserService(mockUserRepo);
    }

    @Test
    public void getId() throws DataBaseException {
        Mockito.when(mockUserRepo.getId(Mockito.anyString())).thenReturn(42L);
        assertEquals(42L, userService.getId(Mockito.anyString()));
    }

    @Test
    public void getUser() throws DataBaseException {
        User user = new User();
        Mockito.when(mockUserRepo.get(Mockito.anyLong())).thenReturn(user);
        assertEquals(user, userService.get(Mockito.anyLong()));
    }

    @Test
    public void createUser() throws DataBaseException {
        Mockito.when(mockUserRepo.createUser(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString())).thenReturn(1);
        assertEquals(1,
                userService.createUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
    }
    @Test
    public void getAll() throws DataBaseException {
        List<User> users = new ArrayList<>();
        Mockito.when(mockUserRepo.getAll()).thenReturn(users);
        assertEquals(users, userService.getAll());
    }

    @Test
    public void deleteUser() throws DataBaseException {
        Mockito.when(mockUserRepo.delete(Mockito.anyLong())).thenReturn(1);
        assertEquals(1, userService.deleteUser(Mockito.anyLong()));
    }

    @Test
    public void updateLarge() throws DataBaseException {
        Mockito.when(mockUserRepo.updateUser(Mockito.anyLong(),
                Mockito.anyString(),  Mockito.anyString(), Mockito.anyBoolean())).thenReturn(1);
        assertEquals(1, userService.updateUser(Mockito.anyLong(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean()));
    }

    @Test
    public void updateSmall() throws DataBaseException {
        Mockito.when(mockUserRepo.updateUser(Mockito.anyLong(),
                Mockito.anyString())).thenReturn(1);
        assertEquals(1, userService.updateUser(Mockito.anyLong(),
                Mockito.anyString()));
    }

    @Test
    public void isBlocked() throws DataBaseException {
        User userTrue = new User();
        userTrue.setBlocked(true);
        Mockito.when(mockUserRepo.get(Mockito.anyLong())).thenReturn(userTrue);
        assertEquals(true, userService.isBlocked(Mockito.anyLong()));
    }

}
