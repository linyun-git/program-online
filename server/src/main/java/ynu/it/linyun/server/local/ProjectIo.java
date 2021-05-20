package ynu.it.linyun.server.local;

import org.springframework.stereotype.Component;
import ynu.it.linyun.server.common.result.PathNode;
import ynu.it.linyun.server.common.util.MyFileUtil;
import ynu.it.linyun.server.entity.Project;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectIo {
    // 仓库实际保存路径
//    private static final String root = "E:/server/projects";
    private static final String root = "/www/service/projects";

    private static final ProjectIo projectIo = new ProjectIo();

    private ProjectIo() {
    }

    /**
     * 使用单例模式构造
     * @return ProjectIo实例
     */
    public static ProjectIo createProjectIo() {
        return projectIo;
    }

    /**
     * 根据相对路径构造绝对路径
     * @param directory 相对路径
     * @return 绝对路径
     */
    public String createPath(String directory) {
        return root + directory;
    }

    /**
     * 创建项目的实际保存目录
     * @param project 项目对象
     */
    public void createProject(Project project) {
        String subPath = "/" + project.getDirectoryCode();
        MyFileUtil.newFolder(createPath(subPath));
    }

    /**
     * 返回项目中的文件结构
     * @param project 项目对象
     * @return 项目中的文件路径结构
     */
    public List<PathNode> getProjectPath(Project project) {
        String subPath = "/" + project.getDirectoryCode();
        String rootPath = createPath(subPath);
        return deepPathNodes(rootPath);
    }

    /**
     * 递归获取某个路径下的文件结构
     * @param rootPath 绝对路径
     * @return 文件结构
     */
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

    /**
     * 获取项目中某个文本文件的内容
     * @param project 项目对象
     * @param path 文件相对路径
     * @return 文本文件内容
     */
    public String getProjectFile(Project project, String path) {
        String subPath = "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        return MyFileUtil.readFile(rootPath);
    }

    /**
     * 将文本写入文本文件中
     * @param project 项目对象
     * @param path 文件相对路径
     * @param content 要写入的内容
     */
    public void saveProjectFile(Project project, String path, String content) {
        String subPath = "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        MyFileUtil.writeFile(rootPath, content);
    }

    /**
     * 创建文件
     * @param project 项目对象
     * @param path 文件相对路径
     */
    public void createProjectFile(Project project, String path) {
        String subPath = "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        MyFileUtil.createFile(rootPath);
    }

    /**
     * 创建目录
     * @param project 项目对象
     * @param path 文件相对路径
     */
    public void createProjectFolder(Project project, String path) {
        String subPath = "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        MyFileUtil.createFolder(rootPath);
    }

    /**
     * 删除文件
     * @param project 项目对象
     * @param path 文件相对路径
     */
    public void deleteProjectFile(Project project, String path) {
        String subPath = "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        MyFileUtil.deleteFile(rootPath);
    }

    /**
     * 删除目录
     * @param project 项目对象
     * @param path 文件相对路径
     */
    public void deleteProjectFolder(Project project, String path) {
        String subPath = "/" + project.getDirectoryCode() + "/" + path;
        String rootPath = createPath(subPath);
        MyFileUtil.deleteFolder(rootPath);
    }
}
