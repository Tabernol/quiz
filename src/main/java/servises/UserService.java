package servises;

import dao.impl.UserDao;
import models.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public long getId(String login) {
        return userDao.getId(login);
    }

    public boolean isCorrectPassword(long id, String password) {
        User user = userDao.get(id);
        //where need create log???
        return password.equals(user.getPassword());
    }

    public String getRoleById(long id){
        User user = userDao.get(id);
        return user.getRole();
    }

    public User get(long id){
        return userDao.get(id);
    }

    public void createUser(String name, String login, String password){
        userDao.createUser(login,password,name);
    }

    public List<User> getAll(){
        return userDao.getAll();
    }
}
