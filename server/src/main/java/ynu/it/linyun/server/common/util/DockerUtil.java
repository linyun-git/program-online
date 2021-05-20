package ynu.it.linyun.server.common.util;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;

/**
 * @author linyun
 * @since 2021-05-05-13:45
 */
public class DockerUtil {
    @Value("${program-online.docker.server}")
    private static String server;
    private static final DockerClient dockerClient;

    private DockerUtil() {
    }

    static {
        dockerClient = DefaultDockerClient.builder()
                .uri(URI.create(server))
                .build();
    }
}
