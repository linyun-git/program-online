package ynu.it.linyun.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import ynu.it.linyun.server.common.exception.BusinessException;
import ynu.it.linyun.server.common.util.UrlUtil;
import ynu.it.linyun.server.entity.*;
import ynu.it.linyun.server.local.SocketConnector;
import ynu.it.linyun.server.service.*;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author linyun
 * @since 2021-05-07-11:13
 */

@Controller
@ServerEndpoint("/terminal/{projectId}")
public class TerminalSocket {

    private static Set<TerminalSocket> socketSet = new CopyOnWriteArraySet<>();

    private Session session;
    private SocketConnector socketConnector;

    private static ProjectService projectService;
    @Autowired
    private void setProjectService(ProjectService projectService) {
        TerminalSocket.projectService = projectService;
    }

    private static UserService userService;
    @Autowired
    private void setUserService(UserService userService) {
        TerminalSocket.userService = userService;
    }

    private static WorkspaceProjectRelationService workspaceProjectRelationService;
    @Autowired
    private void setWorkspaceProjectRelationService(WorkspaceProjectRelationService workspaceProjectRelationService) {
        TerminalSocket.workspaceProjectRelationService = workspaceProjectRelationService;
    }

    private static WorkspaceEnvironmentRelationService workspaceEnvironmentRelationService;
    @Autowired
    private void setWorkspaceEnvironmentRelationService(WorkspaceEnvironmentRelationService workspaceEnvironmentRelationService) {
        TerminalSocket.workspaceEnvironmentRelationService = workspaceEnvironmentRelationService;
    }

    private static EnvironmentService environmentService;
    @Autowired
    private void setEnvironmentService(EnvironmentService environmentService) {
        TerminalSocket.environmentService = environmentService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("projectId") Integer projectId) throws Exception {
        this.session = session;
        Map<String, String> queryParams = UrlUtil.parseQueryParams(session.getQueryString());
        String token = queryParams.get("token");
        User user = userService.getUserByToken(token);
        if (null == user) {
            throw new BusinessException("403");
        }
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", projectId).eq("creator", user.getId());
        Project project = projectService.getOne(queryWrapper);
        if (null == project) {
            throw new BusinessException("404");
        }
        WorkspaceProjectRelation workspaceProjectRelation = workspaceProjectRelationService.getOne(new QueryWrapper<WorkspaceProjectRelation>().eq("project_id", project.getId()));
        WorkspaceEnvironmentRelation workspaceEnvironmentRelation = workspaceEnvironmentRelationService
                .getOne(new QueryWrapper<WorkspaceEnvironmentRelation>().eq("workspace_id", workspaceProjectRelation.getWorkspaceId()));
        Environment environment = environmentService.getOne(new QueryWrapper<Environment>().eq("id", workspaceEnvironmentRelation.getEnvironmentId()));
        this.socketConnector = SocketConnector.createSocketConnector(this, project, environment);
        socketSet.add(this);
    }

    @OnClose
    public void onClose() {
        socketSet.remove(this);
        socketConnector.onClose();
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        if (socketConnector == null) {
            return;
        }
        try {
            this.socketConnector.sendToDocker(ByteBuffer.wrap(message.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
    }

    public void sendMessage(String message) throws Exception {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    public void sendMessage(ByteBuffer message) throws IOException {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendBinary(message);
        }
    }

    public void close() {
        if (!this.session.isOpen()) {
            return;
        }
        try {
            this.session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
