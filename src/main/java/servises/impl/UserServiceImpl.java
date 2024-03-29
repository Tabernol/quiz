package servises.impl;

import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.User;
import repo.UserRepo;
import servises.*;
import util.converter.ConvertToDtoAble;
import util.converter.ConvertToEntityAble;
import util.query.MyQuery;
import util.query.QueryBuilderForUser;
import util.query.QueryCreator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call UserRepo.class or throw an exception
 */
@Slf4j
public class UserServiceImpl implements UserService,
        ConvertToDtoAble<UserDto, User>,
        ConvertToEntityAble<User, UserDto> {
    /**
     * Class contains:
     * userRepo field for work with UserRepo.class
     * validatorService field for validate input date from other
     */
    private final UserRepo userRepo;
    private final ValidatorService validatorService;

    public UserServiceImpl(UserRepo userRepo, ValidatorService validatorService) {
        this.userRepo = userRepo;
        this.validatorService = validatorService;
    }

    /**
     * This method calls to UserRepo.class to get user
     *
     * @param id is unique number of user
     * @return models.User
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public UserDto get(Long id) throws DataBaseException {
        log.info("SERVICE USER get user with id {}", id);
        return mapToDto(userRepo.get(id));
    }


    /**
     * This method validates the input parameters.
     * it checks the login for uniqueness.
     * it checks password and repeatPassword
     * and hashing password before insert to database
     *
     * @param userDto        contains data of:
     *                       name is name of user
     *                       login is unique email address of user
     * @param password       is user`s password
     * @param repeatPassword is repeat password
     * @return id of new user
     * @throws DataBaseException is wrapper of SQLException
     * @throws ValidateException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @Override
    public Long createUser(UserDto userDto, String password, String repeatPassword)
            throws DataBaseException, ValidateException, NoSuchAlgorithmException, InvalidKeySpecException {
        validatorService.validateFieldsUser(userDto.getName(), userDto.getLogin(), password);
        validatorService.isLoginExist(userRepo.isLoginExist(userDto.getLogin()));
        validatorService.validateRepeatPassword(password, repeatPassword);
        String passwordHash = PasswordHashingService.generateStrongPasswordHash(password);
        log.info("SERVICE USER create new user {}", userDto);

        return userRepo.create(new User(userDto.getLogin(), passwordHash, userDto.getName()));
    }

    /**
     * this method calls to UserRepo.class for return List of all users
     *
     * @return List of all users from database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<UserDto> getAll() throws DataBaseException {
        log.info("SERVICE USER get all users");
        return mapToDtoList(userRepo.getAll());
    }

    /**
     * this method calls validate input parameters and calls to UserRepo.class
     *
     * @param userdto contains information for update user in database
     * @return 1 if user has updated
     * @throws DataBaseException is wrapper of SQLException
     * @throws ValidateException
     */
    @Override
    public int update(UserDto userdto) throws DataBaseException, ValidateException {
        validatorService.validateUpdateUser(userdto.getName(), userdto.getRole());
        log.info("SERVICE USER update user {}", userdto);
        return userRepo.update(mapToEntity(userdto));
    }

    /**
     * this method calls to UserRepo.class for get id
     *
     * @param login is unique email address of user
     * @return id of user
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public long getId(String login) throws DataBaseException {
        log.info("SERVICE USER get id user with login {}", login);
        return userRepo.getId(login);
    }

    /**
     * This method change status of user on opposite
     *
     * @param userId is unique number of user in database
     * @return 1 if user has updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public boolean blockUnBlockUser(Long userId) throws DataBaseException {
        boolean status = get(userId).isBlocked();
        userRepo.changeStatus(userId, !status);
        log.info("SERVICE USER  change status user");
        return get(userId).isBlocked();
    }


    /**
     * This method calls to UserRepo.class to get number of users with chose status
     *
     * @param status can be 'BLOCKED' 'UNBLOCKED' or 'all'
     * @return number of user
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCountUsers(String status) throws DataBaseException {
        if (status.equals("all")) {
            log.info("SERVICE USER get all users");
            return userRepo.getAll().size();
        } else {
            log.info("SERVICE USER get users with status = {}", status);
            return userRepo.getCountUsers(status);
        }
    }

    /**
     * This calls to util class QueryBuilderForUser
     * and received ready query and call to UserRepo.class for received some list of users
     *
     * @param filter can be 'block', 'unblock' or 'all'
     * @param order  is order of users was selected during filtering
     * @param rows   is number of rows on one page was selected during filtering
     * @param page   s page of chosen tests
     * @return list of models.User
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<UserDto> nextPage(String filter, String order, Integer rows, Integer page) throws DataBaseException {
        QueryCreator queryCreator = new QueryBuilderForUser();
        String query = queryCreator.getSQL(new MyQuery(filter, order, rows, page));
        log.info("SERVICE USER get list of user with selected filter");
        return mapToDtoList(userRepo.nextPage(query));
    }

    /**
     * this method counts the number of pages for the selected filter by status and number of rows
     *
     * @param status be 'BLOCKED' 'UNBLOCKED' or 'all'
     * @param rows   is number of rows on one page
     * @return count of page for this filter
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int countPages(String status, String rows) throws DataBaseException {
        Integer count = getCountUsers(status);
        Integer rowsOnPage = Integer.valueOf(rows);
        return count % rowsOnPage == 0 ? count / rowsOnPage : count / rowsOnPage + 1;
    }

    /**
     * This method compare user`s password with stored password in database
     *
     * @param userId   is unique number of user in database
     * @param password is entered password
     * @return true if hash is the same otherwise return false
     * @throws DataBaseException is wrapper of SQLException
     * @throws ValidateException
     */
    @Override
    public boolean isCorrectPassword(Long userId, String password) throws DataBaseException, ValidateException {
        try {
            String passwordInDataBase = userRepo.get(userId).getPassword();
            log.info("SERVICE USER checking user password");
            return PasswordHashingService.validatePassword(password, passwordInDataBase);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.warn("SERVICE USER problem with verify password");
            throw new ValidateException("Password is not the same " + e);
        } catch (DataBaseException e) {
            log.warn("SERVICE USER problem with storage password in database");
            throw new DataBaseException("Cannot find user in DB " + e);
        }
    }

    @Override
    public UserDto mapToDto(User entity) {
       return UserDto.builder()
               .id(entity.getId())
               .name(entity.getName())
               .login(entity.getLogin())
               .role(entity.getRole().getRole())
               .isBlocked(entity.isBlocked()).build();
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setName(userDto.getName());
        user.setRole(User.Role.valueOf(userDto.getRole().toUpperCase()));
        user.setBlocked(userDto.isBlocked());
        return user;
    }

    private List<UserDto> mapToDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(mapToDto(user));
        }
        log.info(userDtoList.toString());
        return userDtoList;
    }
}
