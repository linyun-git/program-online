package ynu.it.linyun.server.mapper;

import org.springframework.stereotype.Repository;
import ynu.it.linyun.server.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Repository(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

}
