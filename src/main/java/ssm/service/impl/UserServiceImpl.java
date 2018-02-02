package ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.dao.UserDAO;
import ssm.pojo.User;
import ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;


    @Override
    public void addUser(User user) throws Exception {
        if (getUserCountByName(user.getUsername()) > 0) {
            throw new Exception("用户名已存在");
        }
        userDAO.addUser(user);
    }

    @Override
    public int getUserCountByName(String username) {
        return userDAO.getUserCountByName(username);
    }

    @Override
    public User doLogin(String username, String password) throws Exception {
        User user = userDAO.getUserByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("用户名或密码错误");
        }
        return user;
    }
}
