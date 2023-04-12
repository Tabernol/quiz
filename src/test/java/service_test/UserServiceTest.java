package service_test;

import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.UserRepoImpl;
import servises.impl.UserServiceImpl;
import servises.impl.ValidatorServiceImpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    @Mock
    UserRepoImpl mockUserRepoImpl;
    @Mock
    ValidatorServiceImpl mockValidateService;
    UserServiceImpl userService;
    User testUser;

    @BeforeEach
    public void setUp() {
        mockUserRepoImpl = Mockito.mock(UserRepoImpl.class);
        mockValidateService = Mockito.mock(ValidatorServiceImpl.class);
        userService = new UserServiceImpl(mockUserRepoImpl, mockValidateService);
        testUser = new User();
        testUser.setId(12L);
        testUser.setName("newName");
        testUser.setRole(User.Role.ADMIN);
    }

//    @Test
//    public void getId() throws DataBaseException {
//        Mockito.when(mockUserRepo.isLoginExist(Mockito.anyString())).thenReturn(42L);
//        assertEquals(42L, userService.getId(Mockito.anyString()));
//    }

    @Test
    public void getUser() throws DataBaseException {
        User user = new User();
        Mockito.when(mockUserRepoImpl.get(Mockito.anyLong())).thenReturn(user);
        assertEquals(user, userService.get(Mockito.anyLong()));
    }

    @Test
    public void createUser() throws DataBaseException, ValidateException, NoSuchAlgorithmException, InvalidKeySpecException {
        Mockito.when(mockUserRepoImpl.create(testUser)).thenReturn(123L);
        assertEquals(123L,
                userService.createUser(new UserDto("name", "login")
                        , "password", "repeatPassword"));
    }

    @Test
    public void getAll() throws DataBaseException {
        List<User> users = new ArrayList<>();
        Mockito.when(mockUserRepoImpl.getAll()).thenReturn(users);
        assertEquals(users, userService.getAll());
    }

//    @Test
//    public void deleteUser() throws DataBaseException {
//        Mockito.when(mockUserRepo.delete(Mockito.anyLong())).thenReturn(1);
//        assertEquals(1, userService.deleteUser(Mockito.anyLong()));
//    }

    @Test
    public void updateLarge() throws DataBaseException, ValidateException {
        Mockito.when(mockUserRepoImpl.update(testUser)).thenReturn(1);
        assertEquals(1, userService.updateUser(new UserDto(23L,
                "newNAme", "ADMIN")));
    }


//    @Test
//    public void isCorrectPassword() throws DataBaseException, ValidateException {
//        User user = new User();
//        user.setId(123L);
//        user.setRole("student");
//        user.setName("Test");
//        user.setPassword("testPassword");
//        user.setLogin("test@ua.ua");
//        user.setBlocked(false);
//        Mockito.when(mockUserRepo.get(Mockito.anyLong())).thenReturn(user);
//        ;
//        try (MockedStatic<PasswordHashingService> passwordHashingServiceMockedStatic
//                     = Mockito.mockStatic(PasswordHashingService.class)) {
//            passwordHashingServiceMockedStatic.when(() ->
//                    PasswordHashingService.validatePassword("pass", "pass")).thenReturn(true);
//        }
//        Assertions.assertEquals(true,
//                userService.isCorrectPassword(1231235L, "pass"));
//    }

    @Test
    public void isCorrectPasswordThrowEx() throws DataBaseException, ValidateException {
        Mockito.when(mockUserRepoImpl.get(Mockito.anyLong())).thenThrow(new DataBaseException("no user"));
        Assertions.assertThrows(DataBaseException.class,
                () -> userService.isCorrectPassword(235412L, "password"));
    }

    @Test
    public void getCountUsers() throws DataBaseException {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        Mockito.when(mockUserRepoImpl.getAll()).thenReturn(userList);
        Mockito.when(mockUserRepoImpl.getCountUsers("blocked")).thenReturn(20);
        Assertions.assertEquals(1, userService.getCountUsers("all"));
        Assertions.assertEquals(20, userService.getCountUsers("blocked"));
    }

    @Test
    public void getId() throws DataBaseException {
        Mockito.when(mockUserRepoImpl.getId(Mockito.anyString())).thenReturn(123L);
        Assertions.assertEquals(123L, userService.getId(Mockito.anyString()));
    }

    @Test
    public void blockUnblockUser() throws DataBaseException {
        User user = new User();
        user.setId(123L);
        user.setRole(User.Role.STUDENT);
        user.setName("Test");
        user.setPassword("testPassword");
        user.setLogin("test@ua.ua");
        user.setBlocked(false);
        Mockito.when(mockUserRepoImpl.get(Mockito.anyLong())).thenReturn(user);
        Assertions.assertEquals(false, userService.blockUnBlockUser(Mockito.anyLong()));
    }

    @Test
    public void getCountPages() throws DataBaseException {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        Mockito.when(mockUserRepoImpl.getAll()).thenReturn(userList);
        Assertions.assertEquals(1, userService.countPages("all", "2"));
    }

    @Test
    public void getCountPages2() throws DataBaseException {
        Mockito.when(mockUserRepoImpl.getCountUsers("blocked")).thenReturn(20);
        Assertions.assertEquals(10, userService.countPages("blocked", "2"));
    }

//    @Test
//    public void getPageUserList() throws DataBaseException {
//        List<User> userList = new ArrayList<>();
//        Mockito.when(mockUserRepo.nextPage(Mockito.anyString())).thenReturn(userList);
//        Assertions.assertEquals(userList, userService.getPageUserList("filter", "order", 2, 2));
//    }

    @Test
    public void nextPageUserList() throws DataBaseException {
        List<User> userList = new ArrayList<>();
        Mockito.when(mockUserRepoImpl.nextPage(Mockito.anyString())).thenReturn(userList);
        Assertions.assertEquals(userList, userService.nextPage("filter", "order", 2, 2));
    }
}
