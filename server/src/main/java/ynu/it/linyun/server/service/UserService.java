package ynu.it.linyun.server.service;

import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param user 登录用户，包含邮箱和密码
     * @return  登录结果
     */
    public Result login(User user, HttpServletResponse response);

    /**
     * 注册
     * @param user 注册信息，包含邮箱、昵称、密码
     * @return  注册结果
     */
    public Result register(User user, HttpServletResponse response);
    public Result queryList(QueryDto queryDto);
    public User getUserByToken(String token);
}
