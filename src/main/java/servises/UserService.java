package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import repo.UserRepo;
import util.query.QueryBuilderForUser;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class UserService {
    private UserRepo userRepo;
    private ValidatorService validatorService;

    public UserService(UserRepo userRepo, ValidatorService validatorService) {
        this.userRepo = userRepo;
        this.validatorService = validatorService;
    }


    public User get(long id) throws DataBaseException {
        System.out.println(userRepo.get(id));
        return userRepo.get(id);
    }

    public int createUser(String name, String login, String password, String repeatPassword)
            throws DataBaseException, ValidateException, NoSuchAlgorithmException, InvalidKeySpecException {
        validatorService.validateFieldsUser(name, login, password);
        validatorService.isLoginExist(userRepo.isLoginExist(login));
        validatorService.validateRepeatPassword(password, repeatPassword);
        String passwordHash = PasswordHashingService.generateStrongPasswordHash(password);
        return userRepo.createUser(login, passwordHash, name);
    }

    public List<User> getAll() throws DataBaseException {
        return userRepo.getAll();
    }

//    public int deleteUser(Long id) throws DataBaseException {
//        return userRepo.delete(id);
//    }

    public int updateUser(Long id, String name, String role) throws DataBaseException, ValidateException {
        if (role == null) {
            validatorService.validateUpdateUser(name);
            return userRepo.updateUser(id, name);
        } else {
            validatorService.validateUpdateUser(name, role);
            return userRepo.updateUser(id, name, role);
        }
    }

//    public int updateUser(Long id, String name) throws DataBaseException, ValidateException {
//        validatorService.validateUpdateUserName(name);
//        return userRepo.updateUser(id, name);
//    }

    public long getId(String login) throws DataBaseException {
        return userRepo.getId(login);
    }

    public boolean blockUnBlockUser(Long userId) throws DataBaseException {
        boolean status = get(userId).isBlocked();
        userRepo.changeStatus(userId,!status);
        return get(userId).isBlocked();
    }

    private List<User>
    getPageUserList(String filter, String order, Integer rows, Integer page) throws DataBaseException {
        QueryBuilderForUser queryBuilderForUser = new QueryBuilderForUser();
        String query = queryBuilderForUser.getQuery(filter, order, rows, page);
//        QueryFactory queryFactory = new QueryFactory();
//        QBuilder qBuilder = queryFactory.getQueryBuilder(MyTable.USER);
//        qBuilder.setFilter(filter);
//        qBuilder.setOrderBy(order);
//        qBuilder.setLimit(rows);
//        qBuilder.setOffSet(page);
//        String query = qBuilder.getQuery(filter, order, rows, page);
        System.out.println("QUERY = " + query);
        return userRepo.nextPage(query);
    }

    public Integer getCountUsers(String status) throws DataBaseException {
        if (status.equals("all")) {
            return userRepo.getAll().size();
        } else {
            return userRepo.getCountUsers(status);
        }
    }

    public List<User> nextPage(String filter, String order, String rows, String page)
            throws DataBaseException {
        return getPageUserList(filter, order, Integer.valueOf(rows), Integer.valueOf(page));
    }

    public int countPages(String status, String rows) throws DataBaseException {
        Integer count = getCountUsers(status);
        Integer rowsOnPage = Integer.valueOf(rows);
        return count % rowsOnPage == 0 ? count / rowsOnPage : count / rowsOnPage + 1;
    }

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
