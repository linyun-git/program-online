package ynu.it.linyun.server.local;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.Project;

import java.net.URI;

/**
 * @author linyun
 * @since 2021-04-26-19:35
 */
public class DockerIo {

    // docker远程访问地址
    private final static String server = "http://127.0.0.1:2375";
    // docker连接
    private final static DockerClient dockerClient = DefaultDockerClient.builder()
            .uri(URI.create(server))
            .build();

    /**
     * 创建容器
     * @param project 项目对象
     * @param environment 环境对象
     * @return 容器id
     */
    public static String createContainer(Project project, Environment environment) {
        ProjectIo projectIo = ProjectIo.createProjectIo();
        String subPath = "/" + project.getDirectoryCode();
        String path = projectIo.createPath(subPath);
        String containerId = null;
        try {
            ContainerCreation container = dockerClient.createContainer(ContainerConfig.builder()
                    .attachStdin(true)
                    .tty(true)
                    .openStdin(true)
                    .image(environment.getImage()).build());
            dockerClient.startContainer(container.id());
            dockerClient.copyToContainer(new java.io.File(path).toPath(), container.id(), "/root/project");
            containerId = container.id();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return containerId;
    }

    /**
     * 停止并删除容器
     * @param containerId 容器id
     */
    public static void removeContainer(String containerId) {
        try {
            dockerClient.stopContainer(containerId, 0);
            dockerClient.removeContainer(containerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
