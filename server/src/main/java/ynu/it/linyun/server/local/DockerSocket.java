package ynu.it.linyun.server.local;

import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linyun
 * @since 2021-05-07-15:36
 */
public class DockerSocket extends BinaryWebSocketHandler {
    private static final String WS_URI = "ws://192.168.66.131:2375/containers/{containerId}/attach/ws?logs=0&stream=1&stdin=1&stdout=1&stderr=1";
    private final SocketConnector socketConnector;
    private final List<ByteBuffer> messageStack= new ArrayList<>();

    private WebSocketSession session;

    private DockerSocket(SocketConnector socketConnector) {
        super();
        this.socketConnector = socketConnector;
    }

    public static DockerSocket createDockerSocket(SocketConnector socketConnector, String containerId) {
        DockerSocket dockerSocket = new DockerSocket(socketConnector);
        StandardWebSocketClient client = new StandardWebSocketClient();
        new WebSocketConnectionManager(client, dockerSocket, WS_URI.replace("{containerId}", containerId)).start();
        return dockerSocket;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.session = session;
        for (var message: messageStack) {
            session.sendMessage(new BinaryMessage(message));
        }
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        socketConnector.sendToTerminal(message.getPayload());
        super.handleBinaryMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        socketConnector.onClose();
    }

    public void sendMessage(String message) throws IOException {
        this.session.sendMessage(new TextMessage(message));
    }

    public void sendMessage(ByteBuffer message) throws IOException {
        if (this.session == null) {
            this.messageStack.add(message);
            return;
        }
        this.session.sendMessage(new BinaryMessage(message));
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
