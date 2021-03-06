package ynu.it.linyun.server.service;

import ynu.it.linyun.server.common.dto.QueryDto;
import ynu.it.linyun.server.common.result.Result;
import ynu.it.linyun.server.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import ynu.it.linyun.server.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
public interface ProjectService extends IService<Project> {

    public Result queryList(QueryDto queryDto);

    public Result add(User user, Project project, Integer workspaceId);

    public Result pathInfo(User user, Integer projectId);

    public Project getProject(User user, Integer projectId);

    public Result info(User user, Integer projectId);

    public Result fileContent(User user, Integer projectId, String path);

    public Result saveFile(User user, Integer projectId, String path, String content);

    public Result createFile(User user, Integer projectId, String path, String fileType);

    public Result deleteFile(User user, Integer projectId, String path, String fileType);
}
