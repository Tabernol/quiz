package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import repo.UserRepo;

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
            validatorService.validateUpdateUserName(name);
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
        if (get(userId).isBlocked()) {
            userRepo.changeStatus(userId, false);
        } else {
            userRepo.changeStatus(userId, true);
        }
        return get(userId).isBlocked();
    }
}
