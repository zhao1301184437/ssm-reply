package ssm.dao;

import ssm.pojo.User;

public interface UserDAO {

    void addUser(User user);
    //验证用户名是否存在
    int getUserCountByName(String username);

    User getUserByName(String username);
}
