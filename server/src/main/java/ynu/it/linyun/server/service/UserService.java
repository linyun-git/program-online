package ynu.it.linyun.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.mapper.UserMapper;

/**
 * @author linyun
 * @create 2021-04-20-22:47
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectUser(Integer id) {
        return userMapper.selectUser(id);
    }
}
