package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import util.query.MyQuery;
import util.query.QueryBuilderForUser;
import util.query.QueryCreator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call UserRepo.class or throw an exception
 */
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    /**
     * Class contains:
     * userRepo field for work with UserRepo.class
     * validatorService field for validate input date from other
     */
    private UserRepo userRepo;
    private ValidatorService validatorService;

    public UserService(UserRepo userRepo, ValidatorService validatorService) {
        this.userRepo = userRepo;
        this.validatorService = validatorService;
    }

    /**
     * This method calls to UserRepo.class to get user
     *
     * @param id is unique number of user
     * @return models.User
     * @throws DataBaseException
     */
    public User get(long id) throws DataBaseException {
        logger.info("SERVICE USER get user with id " + id);
        return userRepo.get(id);
    }

    /**
     * This method validates the input parameters.
     * it checks the login for uniqueness.
     * it checks password and repeatPassword
     * and hashing password before insert to database
     *
     * @param name           is name of user
     * @param login          is unique email address of user
     * @param password       is user`s password
     * @param repeatPassword is repeat password
     * @return id of new user
     * @throws DataBaseException
     * @throws ValidateException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public Long createUser(String name, String login, String password, String repeatPassword)
            throws DataBaseException, ValidateException, NoSuchAlgorithmException, InvalidKeySpecException {
        validatorService.validateFieldsUser(name, login, password);
        validatorService.isLoginExist(userRepo.isLoginExist(login));
        validatorService.validateRepeatPassword(password, repeatPassword);
        String passwordHash = PasswordHashingService.generateStrongPasswordHash(password);
        logger.info("SERVICE USER create new user");
        return userRepo.createUser(login, passwordHash, name);
    }

    /**
     * this method calls to UserRepo.class for return List of all users
     *
     * @return List of all users from database
     * @throws DataBaseException
     */
    public List<User> getAll() throws DataBaseException {
        logger.info("SERVICE USER get all users");
        return userRepo.getAll();
    }

    /**
     * this method calls validate input parameters and calls to UserRepo.class
     *
     * @param id   is unique number of user in database
     * @param name is new name of user
     * @param role is new role of user
     * @return 1 if user has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int updateUser(Long id, String name, String role) throws DataBaseException, ValidateException {
        if (role == null) {
            validatorService.validateUpdateUser(name);
            logger.info("SERVICE USER update user with id " + id + " new name = " + name);
            return userRepo.updateUser(id, name);
        } else {
            validatorService.validateUpdateUser(name, role);
            logger.info("SERVICE USER update user with id " + id + " new name = " + name + " new role = " + role);
            return userRepo.updateUser(id, name, role);
        }
    }

    /**
     * this method calls to UserRepo.class for get id
     *
     * @param login is unique email address of user
     * @return id of user
     * @throws DataBaseException
     */
    public long getId(String login) throws DataBaseException {
        logger.info("SERVICE USER get id user with login " + login);
        return userRepo.getId(login);
    }

    /**
     * This method change status of user on opposite
     *
     * @param userId is unique number of user in database
     * @return 1 if user has updated
     * @throws DataBaseException
     */
    public boolean blockUnBlockUser(Long userId) throws DataBaseException {
        boolean status = get(userId).isBlocked();
        userRepo.changeStatus(userId, !status);
        logger.info("SERVICE USER  change status user");
        return get(userId).isBlocked();
    }


    /**
     * This method calls to UserRepo.class to get number of users with chose status
     *
     * @param status can be 'BLOCKED' 'UNBLOCKED' or 'all'
     * @return number of user
     * @throws DataBaseException
     */
    public Integer getCountUsers(String status) throws DataBaseException {
        if (status.equals("all")) {
            logger.info("SERVICE USER get all users");
            return userRepo.getAll().size();
        } else {
            logger.info("SERVICE USER get users with status = " + status);
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
     * @throws DataBaseException
     */
    public List<User> nextPage(String filter, String order, Integer rows, Integer page) throws DataBaseException {
        QueryCreator queryCreator = new QueryBuilderForUser();
        String query = queryCreator.getSQL(new MyQuery(filter, order, rows, page));


//        QueryBuilderForUser queryBuilderForUser = new QueryBuilderForUser();
//        String query = queryBuilderForUser.getQuery(filter, order, Integer.valueOf(rows), Integer.valueOf(page), "admin");
        logger.info("SERVICE USER get list of user with selected filter");
        return userRepo.nextPage(query);
    }

    /**
     * this method counts the number of pages for the selected filter by status and number of rows
     *
     * @param status be 'BLOCKED' 'UNBLOCKED' or 'all'
     * @param rows   is number of rows on one page
     * @return count of page for this filter
     * @throws DataBaseException
     */
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
     * @throws DataBaseException
     * @throws ValidateException
     */
    public boolean isCorrectPassword(Long userId, String password) throws DataBaseException, ValidateException {
        try {
            String passwordInDataBase = get(userId).getPassword();
            logger.info("SERVICE USER checking user password");
            return PasswordHashingService.validatePassword(password, passwordInDataBase);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.warn("SERVICE USER problem with verify password");
            throw new ValidateException("Password is not the same " + e);
        } catch (DataBaseException e) {
            logger.warn("SERVICE USER problem with storage password in database");
            throw new DataBaseException("Cannot find user in DB " + e);
        }
    }
}
