package ynu.it.linyun.server.service.impl;

import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.mapper.ProjectMapper;
import ynu.it.linyun.server.service.ProjectService;
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
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}
