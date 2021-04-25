package ynu.it.linyun.server.service;

import ynu.it.linyun.server.common.dto.EnvironmentDto;
import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.User;
import ynu.it.linyun.server.entity.Workspace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
public interface WorkspaceService extends IService<Workspace> {

    public Result queryList(QueryDto queryDto);

    public Result add(User user, Workspace workspace, List<EnvironmentDto> environments);

    public Result info(User user, Integer workspaceId);
}
