package ynu.it.linyun.server.mapper;

import org.springframework.stereotype.Repository;
import ynu.it.linyun.server.entity.User;

/**
 * @author linyun
 * @create 2021-04-20-22:42
 */
@Repository
public interface UserMapper {
    public User selectUser(Integer id);
}
