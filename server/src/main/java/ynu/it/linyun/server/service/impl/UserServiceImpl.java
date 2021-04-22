package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.QueryResult;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import ynu.it.linyun.server.mapper.UserMapper;
import ynu.it.linyun.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("email", user.getEmail())
                .eq("password", user.getPassword());
        User user1 = getOne(queryWrapper);
        if (null == user1) {
            return Result.fail("400").msg("邮箱或密码不正确");
        }
        return Result.success().data(user1);
    }

    @Override
    public Result register(User user) {
        User nameOne = getOne(new QueryWrapper<User>().eq("name", user.getName()));
        if (nameOne != null) {
            return Result.fail("400").msg("昵称已存在");
        }
        User one = getOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        if (one != null) {
            return Result.fail("400").msg("邮箱已存在");
        }
        int id = userMapper.insert(user);
        user.setId(id);
        return Result.success().data(user);
    }

    @Override
    public Result queryList(QueryDto queryDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("name", queryDto.getQuery());
        Page<User> page = new Page<User>(queryDto.getPageIndex(), queryDto.getPageSize());
        userMapper.selectPage(page, queryWrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(page.getRecords());
        queryResult.setCount(page.getTotal());
        return Result.success().data(queryResult);
    }
}
