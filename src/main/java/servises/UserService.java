package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import repo.UserRepo;
import util.query.QueryBuilderForUser;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call UserRepo.class or throw an exception
 */
public class UserService {
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
     * @param id is unique number of user
     * @return models.User
     * @throws DataBaseException
     */
    public User get(long id) throws DataBaseException {
        return userRepo.get(id);
    }

    /**
     * This method validates the input parameters.
     * it checks the login for uniqueness.
     * it checks password and repeatPassword
     * and hashing password before insert to database
     * @param name is name of user
     * @param login is unique email address of user
     * @param password is user`s password
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
        return userRepo.createUser(login, passwordHash, name);
    }

    /**
     * this method calls to UserRepo.class for return List of all users
     * @return List of all users from database
     * @throws DataBaseException
     */
    public List<User> getAll() throws DataBaseException {
        return userRepo.getAll();
    }

    /**
     * this method calls validate input parameters and calls to UserRepo.class
     * @param id is unique number of user in database
     * @param name is new name of user
     * @param role is new role of user
     * @return 1 if user has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int updateUser(Long id, String name, String role) throws DataBaseException, ValidateException {
        if (role == null) {
            validatorService.validateUpdateUser(name);
            return userRepo.updateUser(id, name);
        } else {
            validatorService.validateUpdateUser(name, role);
            return userRepo.updateUser(id, name, role);
        }
    }

    /**
     * this method calls to UserRepo.class for get id
     * @param login is unique email address of user
     * @return id of user
     * @throws DataBaseException
     */
    public long getId(String login) throws DataBaseException {
        return userRepo.getId(login);
    }

    /**
     * This method change status of user on opposite
     * @param userId is unique number of user in database
     * @return 1 if user has updated
     * @throws DataBaseException
     */
    public boolean blockUnBlockUser(Long userId) throws DataBaseException {
        boolean status = get(userId).isBlocked();
        userRepo.changeStatus(userId,!status);
        return get(userId).isBlocked();
    }


    /**
     * This method calls to UserRepo.class to get number of users with chose status
     * @param status can be 'BLOCKED' 'UNBLOCKED' or 'all'
     * @return number of user
     * @throws DataBaseException
     */
    public Integer getCountUsers(String status) throws DataBaseException {
        if (status.equals("all")) {
            return userRepo.getAll().size();
        } else {
            return userRepo.getCountUsers(status);
        }
    }

    /**
     * This calls to util class QueryBuilderForUser
     * and received ready query and call to UserRepo.class for received some list of users
     * @param filter can be 'block', 'unblock' or 'all'
     * @param order is order of users was selected during filtering
     * @param rows is number of rows on one page was selected during filtering
     * @param page s page of chosen tests
     * @return list of models.User
     * @throws DataBaseException
     */
    public List<User> nextPage(String filter, String order, String rows, String page) throws DataBaseException {
        QueryBuilderForUser queryBuilderForUser = new QueryBuilderForUser();
        String query = queryBuilderForUser.getQuery(filter, order, Integer.valueOf(rows), Integer.valueOf(page), "admin");
        System.out.println("QUERY = " + query);
        return userRepo.nextPage(query);
    }
    /**
     * this method counts the number of pages for the selected filter by status and number of rows
     * @param status be 'BLOCKED' 'UNBLOCKED' or 'all'
     * @param rows is number of rows on one page
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
     * @param userId is unique number of user in database
     * @param password is entered password
     * @return true if hash is the same otherwise return false
     * @throws DataBaseException
     * @throws ValidateException
     */
    public boolean isCorrectPassword(Long userId, String password) throws DataBaseException, ValidateException {
        try {
            String passwordInDataBase = get(userId).getPassword();
            return PasswordHashingService.validatePassword(password, passwordInDataBase);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ValidateException("Password is not the same " + e);
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot find user in DB " + e);
        }
    }
}
