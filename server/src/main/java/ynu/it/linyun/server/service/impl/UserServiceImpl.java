package ynu.it.linyun.server.service.impl;

import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.mapper.UserMapper;
import ynu.it.linyun.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
