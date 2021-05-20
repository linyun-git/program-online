package ynu.it.linyun.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.Project;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */

@Repository(value = "environmentMapper")
public interface EnvironmentMapper extends BaseMapper<Environment> {

}
