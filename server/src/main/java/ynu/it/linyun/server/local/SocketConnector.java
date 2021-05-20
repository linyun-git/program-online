package ynu.it.linyun.server.local;

import ynu.it.linyun.server.controller.TerminalSocket;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.Project;

import java.nio.ByteBuffer;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author linyun
 * @since 2021-05-07-15:36
 */

public class SocketConnector {
    // 所有有效的连接
    private static final Set<SocketConnector> connectors = new CopyOnWriteArraySet<>();

    // 连接浏览器端实例
    private TerminalSocket terminalSocket;
    // 连接docker实例
    private DockerSocket dockerSocket;
    // 容器id
    private String containerId;

    private SocketConnector() {
    }

    /**
     * 创建SocketConnector实例的工厂方法，需要启动容器和创建DockerSocket实例
     * @param terminalSocket TerminalSocket实例
     * @param project 项目对象
     * @param environment 环境对象
     * @return SocketConnector实例
     */
    public static SocketConnector createSocketConnector(TerminalSocket terminalSocket, Project project, Environment environment) {
        String containerId = DockerIo.createContainer(project, environment);
        SocketConnector socketConnector = new SocketConnector();
        socketConnector.terminalSocket = terminalSocket;
        socketConnector.dockerSocket = DockerSocket.createDockerSocket(socketConnector, containerId);
        connectors.add(socketConnector);
        return socketConnector;
    }

    /**
     * 关闭连接时执行
     */
    public void onClose() {
        terminalSocket.close();
        dockerSocket.close();
        DockerIo.removeContainer(containerId);
        connectors.remove(this);
    }

    /**
     * 将数据发送给容器
     * @param message 数据
     */
    public void sendToDocker(ByteBuffer message) {
        if (dockerSocket == null) {
            return;
        }
        try {
            dockerSocket.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据发送给客户端
     * @param message 数据
     */
    public void sendToTerminal(ByteBuffer message) {
        if (terminalSocket == null) {
            return;
        }
        try {
            terminalSocket.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
