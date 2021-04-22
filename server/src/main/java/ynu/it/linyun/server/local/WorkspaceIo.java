package ynu.it.linyun.server.local;

import org.springframework.stereotype.Component;
import ynu.it.linyun.server.common.util.FileUtil;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.entity.Workspace;

@Component
public class WorkspaceIo {
//    private String root = "E:/server/workspaces/";
    private String root = "/www/service/workspaces";

    private String createPath(String directory) {
        return root + directory;
    }

    public void createWorkspace(Workspace workspace) {
        String subPath = workspace.getDirectoryCode();
        FileUtil.newFolder(createPath(subPath));
    }

    public void deleteWorkspace(Workspace workspace) {
        String subPath = workspace.getDirectoryCode();
        FileUtil.deleteFolder(createPath(subPath));
    }

    public void createProject(Workspace workspace, Project project) {
        String subPath = workspace.getDirectoryCode() + "/" + project.getDirectoryCode();
        FileUtil.newFolder(createPath(subPath));
    }
}
