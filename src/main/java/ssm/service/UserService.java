package ssm.service;

import ssm.pojo.User;

public interface UserService {
    void addUser(User user) throws  Exception;
    int getUserCountByName(String username);

    User doLogin(String username, String password) throws Exception;
}
