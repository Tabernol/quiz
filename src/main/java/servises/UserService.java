package servises;

import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UserService {

    UserDto get(Long id) throws DataBaseException;

    Long createUser(UserDto userDto, String password, String repeatPassword)
            throws DataBaseException, ValidateException, NoSuchAlgorithmException, InvalidKeySpecException;

    List<UserDto> getAll() throws DataBaseException;

    int update(UserDto userdto) throws DataBaseException, ValidateException;

    long getId(String login) throws DataBaseException;

    boolean blockUnBlockUser(Long userId) throws DataBaseException;

    Integer getCountUsers(String status) throws DataBaseException;

    List<UserDto> nextPage(String filter, String order, Integer rows, Integer page) throws DataBaseException;

    int countPages(String status, String rows) throws DataBaseException;

    boolean isCorrectPassword(Long userId, String password) throws DataBaseException, ValidateException;
}
