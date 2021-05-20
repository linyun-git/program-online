package ynu.it.linyun.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.it.linyun.server.entity.*;
import ynu.it.linyun.server.mapper.EnvironmentMapper;
import ynu.it.linyun.server.service.EnvironmentService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Service
public class EnvironmentServiceImpl extends ServiceImpl<EnvironmentMapper, Environment> implements EnvironmentService {
}
