package servises;

import exeptions.DataBaseException;
import models.User;
import repo.UserRepo;

import java.util.List;

public class UserService {
    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public long getId(String login) throws DataBaseException {
        return userRepo.getId(login);
    }

    public User get(long id) throws DataBaseException {
        return userRepo.get(id);
    }

    public int createUser(String name, String login, String password) throws DataBaseException {
        return userRepo.createUser(login, password, name);
    }

    public List<User> getAll() throws DataBaseException {
        return userRepo.getAll();
    }

    public int deleteUser(Long id) throws DataBaseException {
       return userRepo.delete(id);
    }

    public int updateUser(Long id, String name, String login, String role, boolean status) throws DataBaseException {
        return userRepo.updateUser(id, name, login, role, status);
    }

    public int updateUser(Long id, String name, String login) throws DataBaseException {
        return userRepo.updateUser(id, name, login);
    }

    public boolean isBlocked(Long id) throws DataBaseException {
        return userRepo.get(id).isBlocked();
    }
}
