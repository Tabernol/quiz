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

    public int createUser(String name, String login, String password)
            throws DataBaseException, ValidateException, NoSuchAlgorithmException, InvalidKeySpecException {
        validatorService.validateFieldsUser(name,login,password);
        validatorService.isLoginExist(userRepo.isLoginExist(login));
        String passwordHash = PasswordHashingService.generateStrongPasswordHash(password);
        return userRepo.createUser(login, passwordHash, name);
    }

    public List<User> getAll() throws DataBaseException {
        return userRepo.getAll();
    }

    public int deleteUser(Long id) throws DataBaseException {
       return userRepo.delete(id);
    }

    public int updateUser(Long id, String name, String role, boolean status) throws DataBaseException {
        return userRepo.updateUser(id, name, role, status);
    }

    public int updateUser(Long id, String name) throws DataBaseException {
        return userRepo.updateUser(id, name);
    }

    public boolean isBlocked(Long id) throws DataBaseException {
        return userRepo.get(id).isBlocked();
    }

    public long getId(String login) throws DataBaseException {
        return userRepo.getId(login);
    }
}
