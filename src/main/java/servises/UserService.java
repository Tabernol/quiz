package servises;

import exeptions.DataBaseException;
import models.User;
import repo.UserRepo;

import java.util.List;

public class UserService {
    UserRepo userRepo = new UserRepo();

    public long getId(String login) throws DataBaseException {
        return userRepo.getId(login);
    }

    public boolean isCorrectPassword(long id, String password) throws DataBaseException {
        User user = userRepo.get(id);
        //where need create log???
        return password.equals(user.getPassword());
    }

    public String getRoleById(long id) throws DataBaseException {
        User user = userRepo.get(id);
        return user.getRole();
    }

    public User get(long id) throws DataBaseException {
        return userRepo.get(id);
    }

    public void createUser(String name, String login, String password) throws DataBaseException {
        userRepo.createUser(login, password, name);
    }

    public List<User> getAll() throws DataBaseException {
        return userRepo.getAll();
    }

    public void deleteUser(Long id) throws DataBaseException {
        userRepo.delete(id);
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
