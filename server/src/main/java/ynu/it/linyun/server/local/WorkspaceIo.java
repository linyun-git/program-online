package ynu.it.linyun.server.local;

import org.springframework.stereotype.Component;
import ynu.it.linyun.server.common.result.PathNode;
import ynu.it.linyun.server.common.util.MyFileUtil;
import ynu.it.linyun.server.entity.Project;
import ynu.it.linyun.server.entity.Workspace;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkspaceIo {
//    private String root = "E:/server/workspaces";
    private String root = "/www/service/workspaces";

    private String createPath(String directory) {
        return root + directory;
    }

    public void createWorkspace(Workspace workspace) {
        String subPath = "/" + workspace.getDirectoryCode();
        MyFileUtil.newFolder(createPath(subPath));
    }

    public void deleteWorkspace(Workspace workspace) {
        String subPath = "/" + workspace.getDirectoryCode();
        MyFileUtil.deleteFolder(createPath(subPath));
    }

    public void createProject(Workspace workspace, Project project) {
        String subPath = "/" + workspace.getDirectoryCode() + "/" + project.getDirectoryCode();
        MyFileUtil.newFolder(createPath(subPath));
    }

    public List<PathNode> getProjectPath(Workspace workspace, Project project) {
        String subPath = "/" + workspace.getDirectoryCode() + "/" + project.getDirectoryCode();
        String rootPath = createPath(subPath);
        return deepPathNodes(rootPath);
    }

    // 递归获取路径
    private List<PathNode> deepPathNodes(String rootPath) {
        List<PathNode> pathNodes = new ArrayList<>();
        List<String> folders = MyFileUtil.getFolderNameFromFolder(rootPath);
        for (var folder : folders) {
            PathNode pathNode = new PathNode(folder, "folder");
            pathNode.setChildren(deepPathNodes(rootPath + "/" + folder));
            pathNodes.add(pathNode);
        }
        List<String> files = MyFileUtil.getFileNameFromFolder(rootPath);
        for (var file : files) {
            PathNode pathNode = new PathNode(file, "file");
            pathNodes.add(pathNode);
        }
        return pathNodes;
    }

    // 获取文件内容
    public String getProjectFile(Workspace workspace, Project project, String path) {
        String subPath = "/" + workspace.getDirectoryCode() + "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        return MyFileUtil.readFile(rootPath);
    }

    // 写入文件内容
    public void saveProjectFile(Workspace workspace, Project project, String path, String content) {
        String subPath = "/" + workspace.getDirectoryCode() + "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        MyFileUtil.writeFile(rootPath, content);
    }
}
